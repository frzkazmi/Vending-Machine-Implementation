package vending;

import java.awt.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class Main {
public static void main(String[] args)  throws Exception{
		
		VendingMachine vendingMachine =  VendingMachineFactory.createVendingMachine();
		int continuebuy=0;
		
		do{
			operation(vendingMachine);
			System.out.println("Do you want to continue to buy(y/n) ");
			BufferedReader brMore = new BufferedReader(new InputStreamReader(System.in));
			String isMoreCoin = brMore.readLine();
			if(isMoreCoin.equals("y")){
				continuebuy=1;
			}else{
				continuebuy=0;
			}
			
		}while(continuebuy==1);
		
		
		

	}
	
	public static void operation(VendingMachine vendingMachine) throws NumberFormatException, IOException{
		
		int insertedCoin=0;
		int flag=0;
		do{
			
			System.out.println("Insert coin<1,5,10,25>");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));			
			insertedCoin = Integer.parseInt(br.readLine());
			
			Coin[] coins = Coin.values();
			
			for(Coin coin:coins){					
				
				if(coin.getDenomination() ==insertedCoin ){
					Coin insertCoin = coin;
					vendingMachine.insertCoin(insertCoin);
					break;
				}
			}		

			System.out.println("Do you want to Insert more coin(y/n) ");
			BufferedReader brMore = new BufferedReader(new InputStreamReader(System.in));
			String isMoreCoin = brMore.readLine();
			if(isMoreCoin.equals("y")){
				flag=1;
			}else{
				flag=0;
			}
			
		}while(flag==1);
		
		
		String insertedItem=null;
		int itemflag=0;
		int insertedQty;
		do{
			
			System.out.println("Insert ITEM<coke,pepsi,soda> ");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));			
			insertedItem = br.readLine();
			
			System.out.println("Insert Quantity ");
			BufferedReader brQty = new BufferedReader(new InputStreamReader(System.in));			
			insertedQty = Integer.parseInt(br.readLine());
			
			Item[] items = Item.values();
			
			for(Item item:items){					
				
				if(item.getName().equals(insertedItem) ){
					Item insertItem = item;
					vendingMachine.selectItemAndGetPrice(insertItem);
					break;
				}
			}		

			System.out.println("Do you want to Insert more Item(y/n) ");
			BufferedReader brMore = new BufferedReader(new InputStreamReader(System.in));
			String isMoreItem = brMore.readLine();
			if(isMoreItem.equals("y")){
				itemflag=1;
			}else{
				itemflag=0;
			}
			
		}while(itemflag==1);
		
		Bucket<Map<Item,Integer>, List<Coin>> bucket = vendingMachine.collectItemAndChange();
		
		Map<Item,Integer> itemAndQty=bucket.getItems();
		List<Coin> change=bucket.getCoins();
		
		for(Map.Entry<Item, Integer> entry : itemAndQty.entrySet()){
			System.out.print(entry.getKey().getName()+":"+entry.getValue()+",");
		}
		System.out.println("");
		if(change.size()>0){
			System.out.print("Change:");
		}		
		for(Coin coin:change){
			System.out.print(coin.getDenomination()+",");
		}
		System.out.print("");
	}
}