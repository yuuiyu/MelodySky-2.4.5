//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.transformer;

import com.google.common.collect.*;
import com.google.common.base.*;
import com.google.common.io.*;
import java.text.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.util.*;
import java.util.*;
import java.io.*;
import org.apache.commons.io.*;
import org.apache.logging.log4j.*;

public class MixinTransformerModuleInterfaceChecker implements IMixinTransformerModule
{
    private static final Logger logger;
    private final File csv;
    private final File report;
    private final Multimap<ClassInfo, ClassInfo.Method> interfaceMethods;
    
    public MixinTransformerModuleInterfaceChecker() {
        this.interfaceMethods = (Multimap<ClassInfo, ClassInfo.Method>)HashMultimap.create();
        final File debugOutputFolder = new File(MixinTransformer.DEBUG_OUTPUT, "audit");
        debugOutputFolder.mkdirs();
        this.csv = new File(debugOutputFolder, "mixin_implementation_report.csv");
        this.report = new File(debugOutputFolder, "mixin_implementation_report.txt");
        try {
            Files.write((CharSequence)"Class,Method,Signature,Interface\n", this.csv, Charsets.ISO_8859_1);
        }
        catch (IOException ex) {}
        try {
            final String dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            Files.write((CharSequence)("Mixin Implementation Report generated on " + dateTime + "\n"), this.report, Charsets.ISO_8859_1);
        }
        catch (IOException ex2) {}
    }
    
    public void preApply(final TargetClassContext context) {
        final ClassInfo targetClassInfo = context.getClassInfo();
        for (final ClassInfo.Method m : targetClassInfo.getInterfaceMethods(false)) {
            this.interfaceMethods.put((Object)targetClassInfo, (Object)m);
        }
    }
    
    public void postApply(final TargetClassContext context) {
        final ClassInfo targetClassInfo = context.getClassInfo();
        if (targetClassInfo.isAbstract() && !MixinEnvironment.getCurrentEnvironment().getOption(MixinEnvironment.Option.CHECK_IMPLEMENTS_STRICT)) {
            MixinTransformerModuleInterfaceChecker.logger.info("{} is skipping abstract target {}", new Object[] { this.getClass().getSimpleName(), context });
            return;
        }
        final String className = targetClassInfo.getName().replace('/', '.');
        int missingMethodCount = 0;
        final PrettyPrinter printer = new PrettyPrinter();
        printer.add("Class: %s", className).hr();
        printer.add("%-32s %-47s  %s", "Return Type", "Missing Method", "From Interface").hr();
        final Set<ClassInfo.Method> interfaceMethods = (Set<ClassInfo.Method>)targetClassInfo.getInterfaceMethods(true);
        final Set<ClassInfo.Method> implementedMethods = new HashSet<ClassInfo.Method>(targetClassInfo.getSuperClass().getInterfaceMethods(true));
        implementedMethods.addAll(this.interfaceMethods.removeAll((Object)targetClassInfo));
        for (final ClassInfo.Method method : interfaceMethods) {
            final ClassInfo.Method found = targetClassInfo.findMethodInHierarchy(method.getName(), method.getDesc(), ClassInfo.SearchType.ALL_CLASSES, ClassInfo.Traversal.ALL);
            if (found != null && !found.isAbstract()) {
                continue;
            }
            if (implementedMethods.contains(method)) {
                continue;
            }
            if (missingMethodCount > 0) {
                printer.add();
            }
            final SignaturePrinter signaturePrinter = new SignaturePrinter(method.getName(), method.getDesc()).setModifiers("");
            final String iface = method.getOwner().getName().replace('/', '.');
            ++missingMethodCount;
            printer.add("%-32s%s", signaturePrinter.getReturnType(), signaturePrinter);
            printer.add("%-80s  %s", "", iface);
            this.appendToCSVReport(className, method, iface);
        }
        if (missingMethodCount > 0) {
            printer.hr().add("%82s%s: %d", "", "Total unimplemented", missingMethodCount);
            printer.print(System.err);
            this.appendToTextReport(printer);
        }
    }
    
    private void appendToCSVReport(final String className, final ClassInfo.Method method, final String iface) {
        try {
            Files.append((CharSequence)String.format("%s,%s,%s,%s\n", className, method.getName(), method.getDesc(), iface), this.csv, Charsets.ISO_8859_1);
        }
        catch (IOException ex) {}
    }
    
    private void appendToTextReport(final PrettyPrinter printer) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(this.report, true);
            final PrintStream stream = new PrintStream(fos);
            stream.print("\n");
            printer.print(stream);
        }
        catch (Exception ex) {}
        finally {
            IOUtils.closeQuietly((OutputStream)fos);
        }
    }
    
    static {
        logger = LogManager.getLogger("mixin");
    }
}
