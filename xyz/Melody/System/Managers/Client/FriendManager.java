//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.System.Managers.Client;

import xyz.Melody.System.Managers.*;
import xyz.Melody.*;
import xyz.Melody.System.Commands.*;
import java.util.*;

public class FriendManager implements Manager
{
    private static HashMap<String, String> friends;
    
    @Override
    public void init() {
        FriendManager.friends = new HashMap<String, String>();
        final List<?> frriends = (List<?>)FileManager.read("Friends.txt");
        for (final String v : frriends) {
            if (v.contains(":")) {
                final String name = v.split(":")[0];
                final String alias = v.split(":")[1];
                FriendManager.friends.put(name, alias);
            }
            else {
                FriendManager.friends.put(v, v);
            }
        }
        Client.instance.getCommandManager().add((Command)new ll(this, "f", new String[] { "friend", "fren", "fr" }, "add/del/list name alias", "Manage client friends"));
    }
    
    public static boolean isFriend(final String name) {
        return FriendManager.friends.containsKey(name);
    }
    
    public static String getAlias(final Object friends2) {
        return FriendManager.friends.get(friends2);
    }
    
    public static HashMap<String, String> getFriends() {
        return FriendManager.friends;
    }
    
    static HashMap<String, String> access$0() {
        return FriendManager.friends;
    }
}
