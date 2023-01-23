//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package utils.hodgepodge.object;

public final class Packer<I>
{
    private volatile I packingItem;
    
    public Packer() {
    }
    
    public Packer(final I packingItem) {
        this.packingItem = packingItem;
    }
    
    public I getPackingItem() {
        return this.packingItem;
    }
    
    public void setPackingItem(final I packingItem) {
        this.packingItem = packingItem;
    }
}
