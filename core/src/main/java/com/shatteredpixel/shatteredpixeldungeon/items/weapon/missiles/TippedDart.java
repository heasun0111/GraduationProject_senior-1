/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015  Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2017 Evan Debenham
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.PinCushion;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.Recipe;
import com.shatteredpixel.shatteredpixeldungeon.plants.Earthroot;
import com.shatteredpixel.shatteredpixeldungeon.plants.Firebloom;
import com.shatteredpixel.shatteredpixeldungeon.plants.Plant;

import java.util.ArrayList;

public abstract class TippedDart extends Dart {
	
	{
		bones = true;
	}
	
	@Override
	public int STRReq(int lvl) {
		return 11;
	}
	
	@Override
	protected void rangedHit(Char enemy, int cell) {
		if (enemy.isAlive())
			Buff.affect(enemy, PinCushion.class).stick(new Dart());
		else
			Dungeon.level.drop( new Dart(), cell ).sprite.drop();
	}
	
	@Override
	public int price() {
		return 6 * quantity;
	}
	
	
	public static class TipDart extends Recipe{
		
		@Override
		//also sorts ingredients if it can
		public boolean testIngredients(ArrayList<Item> ingredients) {
			if (ingredients.size() != 2) return false;
			
			if (ingredients.get(0).getClass() == Dart.class){
				if (!(ingredients.get(1) instanceof Plant.Seed)){
					return false;
				}
			} else if (ingredients.get(0) instanceof Plant.Seed){
				if (ingredients.get(1).getClass() == Dart.class){
					Item temp = ingredients.get(0);
					ingredients.set(0, ingredients.get(1));
					ingredients.set(1, temp);
				} else {
					return false;
				}
			} else {
				return false;
			}
			
			Plant.Seed seed = (Plant.Seed) ingredients.get(1);
			
			if (ingredients.get(0).quantity() >= 2
					&& seed.quantity() >= 1
					&& (seed instanceof Firebloom.Seed || seed instanceof Earthroot.Seed)){
				return true;
			}
			
			return false;
		}
		
		@Override
		public int cost(ArrayList<Item> ingredients) {
			return 2;
		}
		
		@Override
		public Item brew(ArrayList<Item> ingredients) {
			if (!testIngredients(ingredients)) return null;
			
			ingredients.get(0).quantity(ingredients.get(0).quantity() - 2);
			ingredients.get(1).quantity(ingredients.get(1).quantity() - 1);
			
			if (ingredients.get(1) instanceof Firebloom.Seed){
				return new IncendiaryDart().quantity(2);
			} else {
				return new CurareDart().quantity(2);
			}
		}
		
		@Override
		public Item sampleOutput(ArrayList<Item> ingredients) {
			if (!testIngredients(ingredients)) return null;
			
			if (ingredients.get(1) instanceof Firebloom.Seed){
				return new IncendiaryDart().quantity(2);
			} else {
				return new CurareDart().quantity(2);
			}
		}
	}
}