//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface rdfIDataSource extends nsISupports
{
    public static final String RDFIDATASOURCE_IID = "{ebce86bd-1568-4a34-a808-9ccf9cde8087}";
    
    void visitAllSubjects(final rdfITripleVisitor p0);
    
    void visitAllTriples(final rdfITripleVisitor p0);
}
