//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.core;

import chrriis.dj.nativeswing.common.*;
import chrriis.dj.nativeswing.swtimpl.*;
import java.io.*;
import java.util.*;

public class DefaultPeerVMProcessFactory implements PeerVMProcessFactory
{
    @Override
    public Process createProcess(final String[] classpathItems, final Map<String, String> systemPropertiesMap, final String[] vmParams, final String mainClass, final String[] mainClassParameters) {
        final String pathSeparator = SystemProperty.PATH_SEPARATOR.get();
        final String[] candidateBinaries = { new File(SystemProperty.JAVA_HOME.get(), "bin/java").getAbsolutePath(), new File("/usr/lib/java").getAbsolutePath(), "java" };
        boolean isTryingAppletCompatibility = true;
        for (final String peerVMParam : vmParams) {
            if (peerVMParam.startsWith("-Xbootclasspath/a:")) {
                isTryingAppletCompatibility = false;
                break;
            }
        }
        final String javaVersion = SystemProperty.JAVA_VERSION.get();
        String vmParamsWithAppletCompatibility = null;
        if (isTryingAppletCompatibility && javaVersion != null && javaVersion.compareTo("1.6.0_10") >= 0 && "Sun Microsystems Inc.".equals(SystemProperty.JAVA_VENDOR.get())) {
            final String javaHome = SystemProperty.JAVA_HOME.get();
            final File[] deploymentFiles = { new File(javaHome, "lib/deploy.jar"), new File(javaHome, "lib/plugin.jar"), new File(javaHome, "lib/javaws.jar") };
            final StringBuilder sbX = new StringBuilder();
            for (int i = 0; i < deploymentFiles.length; ++i) {
                if (i != 0) {
                    sbX.append(pathSeparator);
                }
                final File deploymentFile = deploymentFiles[i];
                if (deploymentFile.exists()) {
                    sbX.append(deploymentFile.getAbsolutePath());
                }
            }
            if (sbX.indexOf(" ") != -1) {
                vmParamsWithAppletCompatibility = "\"-Xbootclasspath/a:" + (Object)sbX + "\"";
            }
            else {
                vmParamsWithAppletCompatibility = "-Xbootclasspath/a:" + (Object)sbX;
            }
        }
        else {
            isTryingAppletCompatibility = false;
        }
        for (int mode = isTryingAppletCompatibility ? 1 : 0; mode >= 0; --mode) {
            final List<String> argList = new ArrayList<String>();
            final String[] array = candidateBinaries;
            final int length2 = array.length;
            final int n = 0;
            if (n < length2) {
                final String candidateBinary = array[n];
                argList.add(candidateBinary);
                if (mode == 1) {
                    argList.add(vmParamsWithAppletCompatibility);
                }
                for (final String vmParam : vmParams) {
                    argList.add(vmParam);
                }
                for (final Map.Entry<String, String> propertyEntry : systemPropertiesMap.entrySet()) {
                    String value = propertyEntry.getValue();
                    if (Utils.IS_WINDOWS) {
                        value = value.replace("\\\"", "\"").replace("\"", "\\\"");
                    }
                    argList.add("-D" + propertyEntry.getKey() + "=" + value);
                }
                argList.add("-classpath");
                final StringBuilder sb = new StringBuilder();
                for (int j = 0; j < classpathItems.length; ++j) {
                    if (j > 0) {
                        sb.append(pathSeparator);
                    }
                    sb.append(classpathItems[j]);
                }
                argList.add(sb.toString());
                argList.add(mainClass);
                for (final String mainClassParameter : mainClassParameters) {
                    argList.add(mainClassParameter);
                }
                if (Boolean.parseBoolean(NSSystemPropertySWT.PEERVM_DEBUG_PRINTCOMMANDLINE.get())) {
                    System.err.println("Native Command: " + Arrays.toString(argList.toArray()));
                }
                try {
                    return new ProcessBuilder(argList).start();
                }
                catch (IOException e) {
                    throw new IllegalStateException(e);
                }
            }
        }
        return null;
    }
}
