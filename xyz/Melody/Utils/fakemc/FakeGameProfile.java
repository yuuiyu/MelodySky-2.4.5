//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Utils.fakemc;

import com.mojang.authlib.*;
import java.util.*;
import com.mojang.authlib.properties.*;
import org.apache.commons.lang3.*;
import org.apache.commons.lang3.builder.*;

public class FakeGameProfile extends GameProfile
{
    private UUID id;
    private String name;
    private PropertyMap properties;
    private boolean legacy;
    
    public FakeGameProfile(final UUID id, final String name) {
        super(id, name);
        this.properties = new PropertyMap();
        if (id == null && StringUtils.isBlank((CharSequence)name)) {
            throw new IllegalArgumentException("Name and ID cannot both be blank");
        }
        this.id = id;
        this.name = name;
    }
    
    public UUID getId() {
        return this.id;
    }
    
    public String getName() {
        return this.name;
    }
    
    public PropertyMap getProperties() {
        return this.properties;
    }
    
    public boolean isComplete() {
        return this.id != null && StringUtils.isNotBlank((CharSequence)this.getName());
    }
    
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        final GameProfile that = (GameProfile)o;
        Label_0062: {
            if (this.id != null) {
                if (this.id.equals(that.getId())) {
                    break Label_0062;
                }
            }
            else if (that.getId() == null) {
                break Label_0062;
            }
            return false;
        }
        if (this.name != null) {
            if (this.name.equals(that.getName())) {
                return true;
            }
        }
        else if (that.getName() == null) {
            return true;
        }
        return false;
    }
    
    public int hashCode() {
        int result = (this.id != null) ? this.id.hashCode() : 0;
        result = 31 * result + ((this.name != null) ? this.name.hashCode() : 0);
        return result;
    }
    
    public String toString() {
        return new ToStringBuilder((Object)this).append("id", (Object)this.id).append("name", (Object)this.name).append("properties", (Object)this.properties).append("legacy", this.legacy).toString();
    }
    
    public boolean isLegacy() {
        return this.legacy;
    }
}
