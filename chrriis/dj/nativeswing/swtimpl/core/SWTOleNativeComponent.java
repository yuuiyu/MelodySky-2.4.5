//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.core;

import chrriis.dj.nativeswing.swtimpl.internal.*;
import chrriis.dj.nativeswing.swtimpl.*;
import chrriis.dj.nativeswing.common.*;
import org.eclipse.swt.ole.win32.*;
import java.util.*;
import java.lang.reflect.*;

public abstract class SWTOleNativeComponent extends SWTNativeComponent implements IOleNativeComponent
{
    protected static void configureOleFrame(final OleClientSite site, final OleFrame frame) {
        frame.setData("NS_site", (Object)site);
    }
    
    protected static OleClientSite getSite(final OleFrame frame) {
        final OleClientSite oleClientSite = (OleClientSite)frame.getData("NS_site");
        if (oleClientSite == null) {
            throw new IllegalStateException("The OleNativeComponent is not properly initialized! You need to call configureOleFrame() after the site creation.");
        }
        return oleClientSite;
    }
    
    public void invokeOleFunction(final String functionName, final Object... args) {
        this.invokeOleFunction(new String[] { functionName }, args);
    }
    
    public void invokeOleFunction(final String[] functionPath, final Object... args) {
        this.runAsync((CommandMessage)new CMN_invokeOleFunction(null), new Object[] { false, functionPath, args });
    }
    
    public Object invokeOleFunctionWithResult(final String functionName, final Object... args) {
        return this.invokeOleFunctionWithResult(new String[] { functionName }, args);
    }
    
    public Object invokeOleFunctionWithResult(final String[] functionPath, final Object... args) {
        return this.runSync((CommandMessage)new CMN_invokeOleFunction(null), new Object[] { true, functionPath, args });
    }
    
    public void setOleProperty(final String property, final Object... args) {
        this.setOleProperty(new String[] { property }, args);
    }
    
    public void setOleProperty(final String[] propertyPath, final Object... args) {
        this.runAsync((CommandMessage)new CMN_setOleProperty(null), new Object[] { propertyPath, args });
    }
    
    public Object getOleProperty(final String property, final Object... args) {
        return this.getOleProperty(new String[] { property }, args);
    }
    
    public Object getOleProperty(final String[] propertyPath, final Object... args) {
        return this.runSync((CommandMessage)new CMN_getOleProperty(null), new Object[] { propertyPath, args });
    }
    
    protected static Variant createVariant(final Object value) {
        if (value instanceof Boolean) {
            return new Variant((boolean)value);
        }
        if (value instanceof Short) {
            return new Variant((short)value);
        }
        if (value instanceof Integer) {
            return new Variant((int)value);
        }
        if (value instanceof Long) {
            return new Variant((long)value);
        }
        if (value instanceof Float) {
            return new Variant((float)value);
        }
        if (value instanceof Double) {
            return new Variant((double)value);
        }
        if (value instanceof String || value == null) {
            return new Variant((String)value);
        }
        throw new IllegalArgumentException("The value could not be converted to a Variant: " + value);
    }
    
    protected static Object getVariantValue(final Variant variant) {
        if (variant == null) {
            return null;
        }
        switch (variant.getType()) {
            case 11: {
                return variant.getBoolean();
            }
            case 2: {
                return variant.getShort();
            }
            case 3: {
                return variant.getInt();
            }
            case 20: {
                return variant.getLong();
            }
            case 4: {
                return variant.getFloat();
            }
            case 5: {
                return variant.getDouble();
            }
            case 8: {
                return variant.getString();
            }
            default: {
                throw new IllegalArgumentException("The value could not be converted from a Variant: " + variant);
            }
        }
    }
    
    private static void dispose(final Variant variant) {
        if (variant == null) {
            return;
        }
        variant.dispose();
    }
    
    public void dumpOleInterfaceDefinitions() {
        this.runSync((CommandMessage)new CMN_dumpOleInterfaceDefinitions(null), new Object[0]);
    }
    
    private static class CMN_invokeOleFunction extends ControlCommandMessage
    {
        public Object run(final Object[] args) {
            final String[] propertyPath = (String[])args[1];
            OleAutomation automation = new OleAutomation(SWTOleNativeComponent.getSite((OleFrame)this.getControl()));
            for (int i = 0; i < propertyPath.length; ++i) {
                final int[] ids = automation.getIDsOfNames(new String[] { propertyPath[i] });
                if (ids == null) {
                    automation.dispose();
                    return null;
                }
                if (i == propertyPath.length - 1) {
                    final Object[] vargs = (Object[])args[2];
                    final Variant[] params = new Variant[vargs.length];
                    for (int j = 0; j < vargs.length; ++j) {
                        params[j] = SWTOleNativeComponent.createVariant(vargs[j]);
                    }
                    Object result;
                    if (args[0]) {
                        final Variant resultVariant = automation.invoke(ids[0], params);
                        result = SWTOleNativeComponent.getVariantValue(resultVariant);
                        dispose(resultVariant);
                    }
                    else {
                        result = null;
                        automation.invokeNoReply(ids[0], params);
                    }
                    for (final Variant param : params) {
                        dispose(param);
                    }
                    automation.dispose();
                    return result;
                }
                final Variant variantProperty = automation.getProperty(ids[0]);
                final OleAutomation newAutomation = variantProperty.getAutomation();
                variantProperty.dispose();
                automation.dispose();
                automation = newAutomation;
            }
            automation.dispose();
            return null;
        }
    }
    
    private static class CMN_setOleProperty extends ControlCommandMessage
    {
        public Object run(final Object[] args) {
            final String[] propertyPath = (String[])args[0];
            OleAutomation automation = new OleAutomation(SWTOleNativeComponent.getSite((OleFrame)this.getControl()));
            for (int i = 0; i < propertyPath.length; ++i) {
                final int[] ids = automation.getIDsOfNames(new String[] { propertyPath[i] });
                if (ids == null) {
                    automation.dispose();
                    return false;
                }
                if (i == propertyPath.length - 1) {
                    final Object[] vargs = (Object[])args[1];
                    final Variant[] params = new Variant[vargs.length];
                    for (int j = 0; j < vargs.length; ++j) {
                        params[j] = SWTOleNativeComponent.createVariant(vargs[j]);
                    }
                    final boolean result = automation.setProperty(ids[0], params);
                    for (final Variant param : params) {
                        dispose(param);
                    }
                    automation.dispose();
                    return result;
                }
                final Variant variantProperty = automation.getProperty(ids[0]);
                final OleAutomation newAutomation = variantProperty.getAutomation();
                variantProperty.dispose();
                automation.dispose();
                automation = newAutomation;
            }
            automation.dispose();
            return false;
        }
    }
    
    private static class CMN_getOleProperty extends ControlCommandMessage
    {
        public Object run(final Object[] args) {
            final String[] propertyPath = (String[])args[0];
            OleAutomation automation = new OleAutomation(SWTOleNativeComponent.getSite((OleFrame)this.getControl()));
            for (int i = 0; i < propertyPath.length; ++i) {
                final int[] ids = automation.getIDsOfNames(new String[] { propertyPath[i] });
                if (ids == null) {
                    automation.dispose();
                    return null;
                }
                if (i == propertyPath.length - 1) {
                    final Object[] vargs = (Object[])args[1];
                    final Variant[] params = new Variant[vargs.length];
                    for (int j = 0; j < vargs.length; ++j) {
                        params[j] = SWTOleNativeComponent.createVariant(vargs[j]);
                    }
                    final Variant propertyVariant = automation.getProperty(ids[0], params);
                    for (final Variant param : params) {
                        dispose(param);
                    }
                    final Object result = SWTOleNativeComponent.getVariantValue(propertyVariant);
                    dispose(propertyVariant);
                    automation.dispose();
                    return result;
                }
                final Variant variantProperty = automation.getProperty(ids[0]);
                final OleAutomation newAutomation = variantProperty.getAutomation();
                variantProperty.dispose();
                automation.dispose();
                automation = newAutomation;
            }
            automation.dispose();
            return null;
        }
    }
    
    private static class CMN_dumpOleInterfaceDefinitions extends ControlCommandMessage
    {
        private Map<Short, String> oleTypeToDescriptionMap;
        
        private void dumpOleInterfaceDefinitions(final StringBuilder sb, final OleAutomation automation, final int index) {
            final List<OleFunctionDescription> functionList = new ArrayList<OleFunctionDescription>();
            int i = 0;
            while (true) {
                final OleFunctionDescription functionDescription = automation.getFunctionDescription(i);
                if (functionDescription == null) {
                    break;
                }
                functionList.add(functionDescription);
                ++i;
            }
            Collections.sort(functionList, (Comparator<? super OleFunctionDescription>)new lIlIIII(this));
            final Iterator<OleFunctionDescription> iterator = functionList.iterator();
            while (iterator.hasNext()) {
                final OleFunctionDescription functionDescription = iterator.next();
                for (int j = 0; j < index; ++j) {
                    sb.append("  ");
                }
                sb.append(functionDescription.name).append("(");
                for (int k = 0; k < functionDescription.args.length; ++k) {
                    final OleParameterDescription param = functionDescription.args[k];
                    if (k > 0) {
                        sb.append(", ");
                    }
                    sb.append(this.getTypeDescription(param.type)).append(' ').append((param.name == null) ? ("arg" + k) : param.name);
                }
                sb.append("): ").append(this.getTypeDescription(functionDescription.returnType)).append(Utils.LINE_SEPARATOR);
            }
            final List<String> propertyList = new ArrayList<String>();
            int l = 1;
            while (true) {
                final String name = automation.getName(l);
                if (name == null) {
                    break;
                }
                propertyList.add(name);
                ++l;
            }
            Collections.sort(propertyList, (Comparator<? super String>)new lllIlII(this));
            for (final String propertyName : propertyList) {
                for (int m = 0; m < index; ++m) {
                    sb.append("  ");
                }
                sb.append(propertyName).append(Utils.LINE_SEPARATOR);
                final Variant variantProperty = automation.getProperty(automation.getIDsOfNames(new String[] { propertyName })[0]);
                if (variantProperty != null && variantProperty.getType() == 9) {
                    final OleAutomation newAutomation = variantProperty.getAutomation();
                    this.dumpOleInterfaceDefinitions(sb, newAutomation, index + 1);
                    newAutomation.dispose();
                }
                dispose(variantProperty);
            }
        }
        
        private String getTypeDescription(final short type) {
            String description = this.oleTypeToDescriptionMap.get(type);
            if (description == null) {
                description = '[' + String.valueOf(type) + ']';
            }
            return description;
        }
        
        public Object run(final Object[] args) {
            this.oleTypeToDescriptionMap = new HashMap<Short, String>();
            for (final Field field : OLE.class.getDeclaredFields()) {
                final String fieldName = field.getName();
                Short value = null;
                if (fieldName.startsWith("VT_")) {
                    try {
                        value = (Short)field.get(null);
                    }
                    catch (Exception ex) {}
                }
                if (value != null) {
                    final String fieldDescription = fieldName.substring("VT_".length()).toLowerCase(Locale.ENGLISH);
                    this.oleTypeToDescriptionMap.put(value, fieldDescription);
                }
            }
            final OleAutomation automation = new OleAutomation(SWTOleNativeComponent.getSite((OleFrame)this.getControl()));
            final StringBuilder sb = new StringBuilder();
            this.dumpOleInterfaceDefinitions(sb, automation, 0);
            automation.dispose();
            System.out.print(sb.toString());
            return null;
        }
    }
}
