# Video 6: Simplifying Conditionals and Replacing them with Polymorphism, or with Strategy Pattern

Given an initial code that charges different amounts based on the bag weight and bag quantity:

```Java
public class TurnConditionalIntoMethods {

	public static void main(String[] args){
		
		int[]bagWeight = new int[]{25,55,75};
		
		int numberOfBags = bagWeight.length;
		
		int bagFees = 0;
		
		for(int i = 0; i < numberOfBags; i++){
		
			if(i <= 1){
				if(bagWeight[i] < 50){
					
					if(i == 0) {bagFees+= 25;} else {bagFees+= 35;}
					
				} else if(bagWeight[i] < 70){
                    bagFees += 100;
				}
			} else if (i < 1 && bagWeight[i] < 70){
				bagFees += 150;
			} else {
				bagFees += 200;
			}
		}

		System.out.println("Bag Fees: $" + bagFees);
	}

}

```
The conditional above simply means:
<br>- If bag weight < 50 lbs and there is 1 bag -> $25
<br>- If bag weight < 50 lbs and there is 2 bag -> $35
<br>- If bag weight < 70 lbs regardless of qty -> $200
<br>- If bag weight 50~70 lbs and there is 1 bag -> $100 if first bag
    > $150 for additional bags

<br><br>The above code has a rather complex conditioning statement that can be simplified to become more readable and easily understood. This was done in 2 steps, where the if statement specifically was modified:

<br>First approach:
<br>Below new lines of code added before the main method:

```Java
static int bagOver70lbs()
{ return 200; }
	
static int bagUnder50lbs(int bagNumber)
{ return (bagNumber < 1)?25:35; }

static int bag50to70lbs(int bagNumber)
{ return (bagNumber < 2)?100:150; }
```

Refactored conditional statement:

```Java
bagFees = 0;
		for(int theBag = 0; theBag < numberOfBags; theBag++){
		
			if(bagWeight[theBag] < 50)
			{ bagFees += bagUnder50lbs(theBag); }

			else if (bagWeight[theBag] < 70)
			{ bagFees += bag50to70lbs(theBag); }

			else
			{ bagFees += 200; }
		}
```

Second approach further refactored conditional statement:

```Java
bagFees = 0;
		
		for(int theBag = 0; theBag < numberOfBags; theBag++){
			
			if(bagWeight[theBag] < 50)
			{ bagFees += bagUnder50lbs(theBag); }
			
			if(bagWeight[theBag] < 70 && bagWeight[theBag] >= 50) 
			{ bagFees += bag50to70lbs(theBag); }
			
			if(bagWeight[theBag] >= 70)
			{ bagFees += 200; }
		}
```