//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.xpcom;

import java.nio.charset.*;
import java.io.*;
import java.util.*;

public class INIParser
{
    private HashMap mSections;
    
    public INIParser(final String s, final Charset charset) throws FileNotFoundException, IOException {
        this.initFromFile(new File(s), charset);
    }
    
    public INIParser(final String s) throws FileNotFoundException, IOException {
        this.initFromFile(new File(s), Charset.forName("UTF-8"));
    }
    
    public INIParser(final File file, final Charset charset) throws FileNotFoundException, IOException {
        this.initFromFile(file, charset);
    }
    
    public INIParser(final File file) throws FileNotFoundException, IOException {
        this.initFromFile(file, Charset.forName("UTF-8"));
    }
    
    private void initFromFile(final File file, final Charset charset) throws FileNotFoundException, IOException {
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), charset));
        this.mSections = new HashMap();
        String substring = null;
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            final String trim = line.trim();
            if (trim.length() != 0 && !trim.startsWith("#")) {
                if (trim.startsWith(";")) {
                    continue;
                }
                if (line.startsWith("[")) {
                    if (!trim.endsWith("]") || trim.indexOf("]") != trim.length() - 1) {
                        substring = null;
                    }
                    else {
                        substring = trim.substring(1, trim.length() - 1);
                    }
                }
                else {
                    if (substring == null) {
                        continue;
                    }
                    final StringTokenizer stringTokenizer = new StringTokenizer(line, "=");
                    if (stringTokenizer.countTokens() != 2) {
                        continue;
                    }
                    Properties properties = this.mSections.get(substring);
                    if (properties == null) {
                        properties = new Properties();
                        this.mSections.put(substring, properties);
                    }
                    properties.setProperty(stringTokenizer.nextToken(), stringTokenizer.nextToken());
                }
            }
        }
        bufferedReader.close();
    }
    
    public Iterator getSections() {
        return this.mSections.keySet().iterator();
    }
    
    public Iterator getKeys(final String s) {
        final Properties properties = this.mSections.get(s);
        if (properties == null) {
            return null;
        }
        return new PropertiesIterator(properties.propertyNames());
    }
    
    public String getString(final String s, final String s2) {
        final Properties properties = this.mSections.get(s);
        if (properties == null) {
            return null;
        }
        return properties.getProperty(s2);
    }
    
    class PropertiesIterator implements Iterator
    {
        private Enumeration e;
        
        public PropertiesIterator(final Enumeration e) {
            this.e = e;
        }
        
        @Override
        public boolean hasNext() {
            return this.e.hasMoreElements();
        }
        
        @Override
        public Object next() {
            return this.e.nextElement();
        }
        
        @Override
        public void remove() {
        }
    }
}
