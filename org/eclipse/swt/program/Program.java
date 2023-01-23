//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.program;

import org.eclipse.swt.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import org.eclipse.swt.internal.win32.*;
import org.eclipse.swt.graphics.*;

public final class Program
{
    String name;
    String command;
    String iconName;
    String extension;
    static final String[] ARGUMENTS;
    
    Program() {
    }
    
    static String assocQueryString(final int assocStr, final TCHAR key, final boolean expand) {
        TCHAR pszOut = new TCHAR(0, 1024);
        final int[] pcchOut = { pszOut.length() };
        final int flags = 1056;
        int result = OS.AssocQueryString(flags, assocStr, key, (TCHAR)null, pszOut, (int[])pcchOut);
        if (result == -2147467261) {
            pszOut = new TCHAR(0, pcchOut[0]);
            result = OS.AssocQueryString(flags, assocStr, key, (TCHAR)null, pszOut, (int[])pcchOut);
        }
        if (result != 0) {
            return null;
        }
        if (!expand) {
            return pszOut.toString(0, Math.max(0, pcchOut[0] - 1));
        }
        final int length = OS.ExpandEnvironmentStrings(pszOut, (TCHAR)null, 0);
        if (length != 0) {
            final TCHAR lpDst = new TCHAR(0, length);
            OS.ExpandEnvironmentStrings(pszOut, lpDst, length);
            return lpDst.toString(0, Math.max(0, length - 1));
        }
        return "";
    }
    
    public static Program findProgram(String extension) {
        if (extension == null) {
            SWT.error(4);
        }
        if (extension.length() == 0) {
            return null;
        }
        if (extension.charAt(0) != '.') {
            extension = "." + extension;
        }
        final TCHAR key = new TCHAR(0, extension, true);
        Program program = null;
        final String command = assocQueryString(1, key, true);
        if (command != null) {
            String name = null;
            if (name == null) {
                name = assocQueryString(3, key, false);
            }
            if (name == null) {
                name = assocQueryString(4, key, false);
            }
            if (name == null) {
                name = "";
            }
            String iconName;
            if ((iconName = assocQueryString(15, key, true)) == null) {
                iconName = "";
            }
            program = new Program();
            program.name = name;
            program.command = command;
            program.iconName = iconName;
            program.extension = extension;
        }
        return program;
    }
    
    public static String[] getExtensions() {
        String[] extensions = new String[1024];
        final char[] lpName = new char[1024];
        final int[] lpcName = { lpName.length };
        int dwIndex = 0;
        int count = 0;
        while (OS.RegEnumKeyEx(-2147483648L, dwIndex, (char[])lpName, (int[])lpcName, (int[])null, (char[])null, (int[])null, 0L) != 259) {
            final String extension = new String(lpName, 0, lpcName[0]);
            lpcName[0] = lpName.length;
            if (extension.length() > 0 && extension.charAt(0) == '.') {
                if (count == extensions.length) {
                    final String[] newExtensions = new String[extensions.length + 1024];
                    System.arraycopy(extensions, 0, newExtensions, 0, extensions.length);
                    extensions = newExtensions;
                }
                extensions[count++] = extension;
            }
            ++dwIndex;
        }
        if (count != extensions.length) {
            final String[] newExtension = new String[count];
            System.arraycopy(extensions, 0, newExtension, 0, count);
            extensions = newExtension;
        }
        return extensions;
    }
    
    static String getKeyValue(final String string, final boolean expand) {
        final TCHAR key = new TCHAR(0, string, true);
        final long[] phkResult = { 0L };
        if (OS.RegOpenKeyEx(-2147483648L, key, 0, 131097, (long[])phkResult) != 0) {
            return null;
        }
        String result = null;
        final int[] lpcbData = { 0 };
        if (OS.RegQueryValueEx(phkResult[0], (TCHAR)null, 0L, (int[])null, (TCHAR)null, (int[])lpcbData) == 0) {
            result = "";
            int length = lpcbData[0] / 2;
            if (lpcbData[0] % 2 != 0) {
                ++length;
            }
            final char[] lpData;
            if (length != 0 && OS.RegQueryValueEx(phkResult[0], (char[])null, 0L, (int[])null, (char[])(lpData = new char[length]), (int[])lpcbData) == 0) {
                if (expand) {
                    length = OS.ExpandEnvironmentStrings((char[])lpData, (char[])null, 0);
                    if (length != 0) {
                        final char[] lpDst = new char[length];
                        OS.ExpandEnvironmentStrings((char[])lpData, (char[])lpDst, length);
                        result = new String(lpDst, 0, length - 1);
                    }
                }
                else {
                    result = new String(lpData, 0, length - 1);
                }
            }
        }
        if (phkResult[0] != 0L) {
            OS.RegCloseKey(phkResult[0]);
        }
        return result;
    }
    
    static Program getProgram(final String key, final String extension) {
        String name = getKeyValue(key, false);
        if (name == null || name.length() == 0) {
            name = key;
        }
        final String DEFAULT_COMMAND;
        String defaultCommand;
        if ((defaultCommand = getKeyValue(key + (DEFAULT_COMMAND = "\\shell"), true)) == null || defaultCommand.length() == 0) {
            defaultCommand = "open";
        }
        final String COMMAND;
        final String command;
        if ((command = getKeyValue(key + (COMMAND = "\\shell\\" + defaultCommand + "\\command"), true)) == null || command.length() == 0) {
            return null;
        }
        final String DEFAULT_ICON = "\\DefaultIcon";
        String iconName = getKeyValue(key + DEFAULT_ICON, true);
        if (iconName == null) {
            iconName = "";
        }
        final Program program = new Program();
        program.name = name;
        program.command = command;
        program.iconName = iconName;
        program.extension = extension;
        return program;
    }
    
    public static Program[] getPrograms() {
        final char[] lpName = new char[1024];
        final int[] lpcName = { lpName.length };
        int dwIndex = 0;
        final LinkedHashSet paths = new LinkedHashSet();
        while (OS.RegEnumKeyEx(-2147483648L, dwIndex, (char[])lpName, (int[])lpcName, (int[])null, (char[])null, (int[])null, 0L) != 259) {
            final String path2 = new String(lpName, 0, lpcName[0]);
            lpcName[0] = lpName.length;
            paths.add(path2);
            ++dwIndex;
        }
        final ConcurrentHashMap programs = new ConcurrentHashMap(paths.size());
        final Program program;
        final ConcurrentHashMap<String, Program> concurrentHashMap;
        ((Stream)paths.stream().parallel()).forEach(path -> {
            program = getProgram(path, null);
            if (program != null) {
                concurrentHashMap.put(path, program);
            }
            return;
        });
        final LinkedHashSet sortedPrograms = (LinkedHashSet)paths.stream().map(name -> (Program)programs.get(name)).filter(p -> p != null).collect(Collectors.toCollection(LinkedHashSet::new));
        return (Program[])sortedPrograms.toArray(new Program[sortedPrograms.size()]);
    }
    
    public static boolean launch(final String fileName) {
        return launch(fileName, null);
    }
    
    public static boolean launch(final String fileName, final String workingDir) {
        if (fileName == null) {
            SWT.error(4);
        }
        final long hHeap = OS.GetProcessHeap();
        final TCHAR buffer = new TCHAR(0, fileName, true);
        int byteCount = buffer.length() * 2;
        final long lpFile = OS.HeapAlloc(hHeap, 8, byteCount);
        OS.MoveMemory(lpFile, buffer, byteCount);
        long lpDirectory = 0L;
        if (workingDir != null && OS.PathIsExe(lpFile)) {
            final TCHAR buffer2 = new TCHAR(0, workingDir, true);
            byteCount = buffer2.length() * 2;
            lpDirectory = OS.HeapAlloc(hHeap, 8, byteCount);
            OS.MoveMemory(lpDirectory, buffer2, byteCount);
        }
        final SHELLEXECUTEINFO info = new SHELLEXECUTEINFO();
        info.cbSize = SHELLEXECUTEINFO.sizeof;
        info.lpFile = lpFile;
        info.lpDirectory = lpDirectory;
        info.nShow = 5;
        final boolean result = OS.ShellExecuteEx(info);
        if (lpFile != 0L) {
            OS.HeapFree(hHeap, 0, lpFile);
        }
        if (lpDirectory != 0L) {
            OS.HeapFree(hHeap, 0, lpDirectory);
        }
        return result;
    }
    
    public boolean execute(String fileName) {
        if (fileName == null) {
            SWT.error(4);
        }
        boolean append = true;
        String prefix = this.command;
        String suffix = "";
        for (int index = 0; index < Program.ARGUMENTS.length; ++index) {
            final int i = this.command.indexOf(Program.ARGUMENTS[index]);
            if (i != -1) {
                append = false;
                prefix = this.command.substring(0, i);
                suffix = this.command.substring(i + Program.ARGUMENTS[index].length(), this.command.length());
                break;
            }
        }
        if (append) {
            fileName = " \"" + fileName + "\"";
        }
        final String commandLine = prefix + fileName + suffix;
        final long hHeap = OS.GetProcessHeap();
        final TCHAR buffer = new TCHAR(0, commandLine, true);
        final int byteCount = buffer.length() * 2;
        final long lpCommandLine = OS.HeapAlloc(hHeap, 8, byteCount);
        OS.MoveMemory(lpCommandLine, buffer, byteCount);
        final STARTUPINFO lpStartupInfo = new STARTUPINFO();
        lpStartupInfo.cb = STARTUPINFO.sizeof;
        final PROCESS_INFORMATION lpProcessInformation = new PROCESS_INFORMATION();
        final boolean success = OS.CreateProcess(0L, lpCommandLine, 0L, 0L, false, 0, 0L, 0L, lpStartupInfo, lpProcessInformation);
        if (lpCommandLine != 0L) {
            OS.HeapFree(hHeap, 0, lpCommandLine);
        }
        if (lpProcessInformation.hProcess != 0L) {
            OS.CloseHandle(lpProcessInformation.hProcess);
        }
        if (lpProcessInformation.hThread != 0L) {
            OS.CloseHandle(lpProcessInformation.hThread);
        }
        return success;
    }
    
    public ImageData getImageData() {
        if (this.extension != null) {
            final SHFILEINFO shfi = new SHFILEINFO();
            final int flags = 273;
            final TCHAR pszPath = new TCHAR(0, this.extension, true);
            OS.SHGetFileInfo((char[])pszPath.chars, 128, shfi, SHFILEINFO.sizeof, flags);
            if (shfi.hIcon != 0L) {
                final Image image = Image.win32_new((Device)null, 1, shfi.hIcon);
                final ImageData imageData = image.getImageData();
                image.dispose();
                return imageData;
            }
        }
        int nIconIndex = 0;
        String fileName = this.iconName;
        final int index = this.iconName.indexOf(44);
        if (index != -1) {
            fileName = this.iconName.substring(0, index);
            final String iconIndex = this.iconName.substring(index + 1, this.iconName.length()).trim();
            try {
                nIconIndex = Integer.parseInt(iconIndex);
            }
            catch (NumberFormatException ex) {}
        }
        final int length;
        if ((length = fileName.length()) > 1 && fileName.charAt(0) == '\"' && fileName.charAt(length - 1) == '\"') {
            fileName = fileName.substring(1, length - 1);
        }
        final TCHAR lpszFile = new TCHAR(0, fileName, true);
        final long[] phiconSmall = { 0L };
        final long[] phiconLarge = null;
        OS.ExtractIconEx(lpszFile, nIconIndex, phiconLarge, (long[])phiconSmall, 1);
        if (phiconSmall[0] == 0L) {
            return null;
        }
        final Image image2 = Image.win32_new((Device)null, 1, phiconSmall[0]);
        final ImageData imageData2 = image2.getImageData();
        image2.dispose();
        return imageData2;
    }
    
    public String getName() {
        return this.name;
    }
    
    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof Program) {
            final Program program = (Program)other;
            return this.name.equals(program.name) && this.command.equals(program.command) && this.iconName.equals(program.iconName);
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return this.name.hashCode() ^ this.command.hashCode() ^ this.iconName.hashCode();
    }
    
    @Override
    public String toString() {
        return "Program {" + this.name + "}";
    }
    
    static {
        ARGUMENTS = new String[] { "%1", "%l", "%L" };
    }
}
