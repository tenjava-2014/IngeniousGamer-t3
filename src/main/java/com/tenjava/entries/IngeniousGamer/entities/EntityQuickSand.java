package com.tenjava.entries.IngeniousGamer.entities;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_7_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_7_R3.event.CraftEventFactory;
import org.bukkit.craftbukkit.v1_7_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_7_R3.Block;
import net.minecraft.server.v1_7_R3.Blocks;
import net.minecraft.server.v1_7_R3.EntityFallingBlock;
import net.minecraft.server.v1_7_R3.Material;
import net.minecraft.server.v1_7_R3.MathHelper;
import net.minecraft.server.v1_7_R3.PacketPlayOutWorldParticles;
import net.minecraft.server.v1_7_R3.World;
import com.tenjava.entries.IngeniousGamer.util.Particles;

public class EntityQuickSand extends EntityFallingBlock{	  
	private boolean f;
	private double lastmotX;
	private double lastmotY;
	private double lastmotZ;
	public static ArrayList<EntityQuickSand> allcomets = new ArrayList<EntityQuickSand>();
	public EntityQuickSand(World world, double d0, double d1, double d2,int bid,int bda) {
	      super(world);
	      this.dropItem = true;
	      /* try{
		    Method m = CraftBlock.class.getMethod("getNMSBlock", Integer.class);
		    m.setAccessible(true);
		    m.invoke(m, 1);
		    
			this.id = s;
		    }catch(Exception e){
		    	e.printStackTrace();
		    }*/
		    id = Block.a((CraftItemStack.asNMSCopy(new ItemStack(bid)).getItem()));
		    
		    data = bda;
		    k = true;
		    a(0.98F, 0.98F);
		    height = (this.length / 2.0F);
		    setPosition(d0, d1, d2);
		    lastmotX = 0.0D;
		    lastmotY = 0.0D;
		    lastmotZ = 0.0D;
		    motX = 0.0D;
		    motY = 0.0D;
		    motZ = 0.0D;
		    lastX = d0;
		    lastY = d1;
		    lastZ = d2;
		    allcomets.add(this);
	   }
	@Override
	public void h() {
		if (this.id.getMaterial() == Material.AIR) {
			this.die();
		} else {
			this.lastX = this.locX;
			this.lastY = this.locY;
			this.lastZ = this.locZ;
			lastmotX = this.motX;
			lastmotY = this.motY;
			lastmotZ = this.motZ;
			
			//Bukkit.broadcastMessage("loc");
			++this.ticksLived;
			//this.motY -= 0.00999910593033D;
			//if(!checkCollision(this)){//move back into moving loop
				this.setPosition(locX, locY, locZ);
			//}
			if (!this.world.isStatic) {
				int i = MathHelper.floor(this.locX);
				int j = MathHelper.floor(this.locY);
				int k = MathHelper.floor(this.locZ);
				
				if (this.ticksLived == 1) {
					// CraftBukkit - compare data and call event
					if (this.ticksLived != 1 || this.world.getType(i, j, k) != this.id || this.world.getData(i, j, k) != this.data || CraftEventFactory.callEntityChangeBlockEvent(this, i, j, k, Blocks.AIR, 0).isCancelled()) {
						this.die();
						return;
					}

					this.world.setAir(i, j, k);
				}
					this.motX = 0.0D;
					this.motZ = 0.0D;
					this.velocityChanged=true;
					this.positionChanged=true;
				
				
				
				if (this.onGround) {
				//	this.motX *= 0.699999988079071D;
				//	this.motZ *= 0.699999988079071D;
				//	this.motY *= -0.5D;

				}	
			}	
		}	
	}
}