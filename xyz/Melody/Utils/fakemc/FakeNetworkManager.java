//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Utils.fakemc;

import net.minecraft.network.*;
import java.net.*;
import io.netty.buffer.*;
import io.netty.channel.*;
import io.netty.util.*;

public class FakeNetworkManager extends NetworkManager
{
    public FakeNetworkManager(final EnumPacketDirection packetDirection) {
        super(packetDirection);
    }
    
    public Channel channel() {
        class lI implements Channel
        {
            final /* synthetic */ FakeNetworkManager this$0;
            
            lI(final FakeNetworkManager this$0) {
                this.this$0 = this$0;
            }
            
            public EventLoop eventLoop() {
                return null;
            }
            
            public Channel parent() {
                return null;
            }
            
            public ChannelConfig config() {
                return null;
            }
            
            public boolean isOpen() {
                return false;
            }
            
            public boolean isRegistered() {
                return false;
            }
            
            public boolean isActive() {
                return false;
            }
            
            public ChannelMetadata metadata() {
                return null;
            }
            
            public SocketAddress localAddress() {
                return null;
            }
            
            public SocketAddress remoteAddress() {
                return null;
            }
            
            public ChannelFuture closeFuture() {
                return null;
            }
            
            public boolean isWritable() {
                return false;
            }
            
            public long bytesBeforeUnwritable() {
                return 0L;
            }
            
            public long bytesBeforeWritable() {
                return 0L;
            }
            
            public Channel.Unsafe unsafe() {
                return null;
            }
            
            public ChannelPipeline pipeline() {
                return null;
            }
            
            public ByteBufAllocator alloc() {
                return null;
            }
            
            public ChannelPromise newPromise() {
                return null;
            }
            
            public ChannelProgressivePromise newProgressivePromise() {
                return null;
            }
            
            public ChannelFuture newSucceededFuture() {
                return null;
            }
            
            public ChannelFuture newFailedFuture(final Throwable cause) {
                return null;
            }
            
            public ChannelPromise voidPromise() {
                return null;
            }
            
            public ChannelFuture bind(final SocketAddress localAddress) {
                return null;
            }
            
            public ChannelFuture connect(final SocketAddress remoteAddress) {
                return null;
            }
            
            public ChannelFuture connect(final SocketAddress remoteAddress, final SocketAddress localAddress) {
                return null;
            }
            
            public ChannelFuture disconnect() {
                return null;
            }
            
            public ChannelFuture close() {
                return null;
            }
            
            public ChannelFuture deregister() {
                return null;
            }
            
            public ChannelFuture bind(final SocketAddress localAddress, final ChannelPromise promise) {
                return null;
            }
            
            public ChannelFuture connect(final SocketAddress remoteAddress, final ChannelPromise promise) {
                return null;
            }
            
            public ChannelFuture connect(final SocketAddress remoteAddress, final SocketAddress localAddress, final ChannelPromise promise) {
                return null;
            }
            
            public ChannelFuture disconnect(final ChannelPromise promise) {
                return null;
            }
            
            public ChannelFuture close(final ChannelPromise promise) {
                return null;
            }
            
            public ChannelFuture deregister(final ChannelPromise promise) {
                return null;
            }
            
            public Channel read() {
                return null;
            }
            
            public ChannelFuture write(final Object msg) {
                return null;
            }
            
            public ChannelFuture write(final Object msg, final ChannelPromise promise) {
                return null;
            }
            
            public Channel flush() {
                return null;
            }
            
            public ChannelFuture writeAndFlush(final Object msg, final ChannelPromise promise) {
                return null;
            }
            
            public ChannelFuture writeAndFlush(final Object msg) {
                return null;
            }
            
            public <T> Attribute<T> attr(final AttributeKey<T> key) {
                // 
                // This method could not be decompiled.
                // 
                // Original Bytecode:
                // 
                //     3: dup            
                //     4: aload_0         /* this */
                //     5: invokespecial   xyz/Melody/Utils/fakemc/ll.<init>:(Lxyz/Melody/Utils/fakemc/lI;)V
                //     8: areturn        
                //    Signature:
                //  <T:Ljava/lang/Object;>(Lio/netty/util/AttributeKey<TT;>;)Lio/netty/util/Attribute<TT;>;
                // 
                // The error that occurred was:
                // 
                // java.lang.NullPointerException
                // 
                throw new IllegalStateException("An error occurred while decompiling this method.");
            }
            
            public <T> boolean hasAttr(final AttributeKey<T> key) {
                return false;
            }
            
            public int compareTo(final Channel o) {
                return 0;
            }
        }
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: dup            
        //     4: aload_0         /* this */
        //     5: invokespecial   xyz/Melody/Utils/fakemc/lI.<init>:(Lxyz/Melody/Utils/fakemc/FakeNetworkManager;)V
        //     8: areturn        
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
}
