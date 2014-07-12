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

public class EntityComet extends EntityFallingBlock{	  
	private boolean f;
	private double lastmotX;
	private double lastmotY;
	private double lastmotZ;
	public static ArrayList<EntityComet> allcomets = new ArrayList<EntityComet>();
	public EntityComet(World world, double d0, double d1, double d2,int bid,int bda) {
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
			this.motY -= 0.03999999910593033D;
			//this.motY -= 0.00999910593033D;
			//if(!checkCollision(this)){//move back into moving loop
				this.setPosition(locX, locY, locZ);

			this.move(this.motX, this.motY, this.motZ);
			this.motX *= 0.9800000190734863D;
			this.motY *= 0.9800000190734863D;
			this.motZ *= 0.9800000190734863D;
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
				
				if(this.motX == 0.0D && this.motZ != 0.0D){
					this.motX = lastmotX*-1;
					this.velocityChanged=true;
					//world.getWorld().playSound(new Location(world.getWorld(),locX,locY,locZ), Sound.DIG_WOOD, 2, 1);
					
				}
				if(this.motZ == 0.0D && this.motX != 0.0D){
					this.motZ = lastmotZ*-1;
					this.velocityChanged=true;
					//world.getWorld().playSound(new Location(world.getWorld(),locX,locY,locZ), Sound.DIG_WOOD, 2, 1);
				}
				if(this.motY == 0.0D){
					this.motY = lastmotY*-1;
					this.velocityChanged=true;
					world.getWorld().playSound(new Location(world.getWorld(),locX,locY,locZ), Sound.EXPLODE, 2, 1);
					world.getWorld().createExplosion(new Location(world.getWorld(),locX,locY,locZ),3);
					
					allcomets.remove(this);
					die();
				}
				if(Math.abs(motX) >= 0.01D || Math.abs(motZ) >= 0.01D || Math.abs(motY) >= 0.01D){
		        	try {
						Particles.sendParticle(Particles.FIREWORKS_SPARK, new Location(this.world.getWorld(),this.locX,this.locY,this.locZ), 0.2f, 0f, 0.2f, 0f, 5);
					} catch (Exception e) {
						e.printStackTrace();
					}
		        	
				}
				else if(motX!=0.0D && motZ!=0.0D){
					this.motX = 0.0D;
					this.motZ = 0.0D;
					this.velocityChanged=true;
				}
				
				
				
				if (this.onGround) {
				//	this.motX *= 0.699999988079071D;
				//	this.motZ *= 0.699999988079071D;
				//	this.motY *= -0.5D;

				}	
			}	
		}	
	}
}