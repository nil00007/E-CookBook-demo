import java.util.LinkedList;
import java.util.Queue;

// TreeNode sınıfı, ağaçtaki düğümleri temsil eder
class TreeNode {
    String data;
    TreeNode left, right;
    int height;

    public TreeNode(String data) {
        this.data = data;
        this.height = 1;
    }
}

// AVLTree sınıfı, AVL ağacını temsil eder
class AVLTree {
    TreeNode root;

    // Ağacın yüksekliğini döndüren yardımcı metot
    int height(TreeNode node) {
        if (node == null)
            return 0;
        return node.height;
    }

    // Yeni yüksekliği hesaplayan yardımcı metot
    int newHeight(TreeNode node) {
        if (node == null)
            return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    // Denge faktörünü hesaplayan yardımcı metot
    int balanceFactor(TreeNode node) {
        if (node == null)
            return 0;
        return height(node.left) - height(node.right);
    }

    // Düğümleri sağa döndüren yardımcı metot
    TreeNode rotateRight(TreeNode y) {
        TreeNode x = y.left;
        TreeNode T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = newHeight(y);
        x.height = newHeight(x);

        return x;
    }

    // Düğümleri sola döndüren yardımcı metot
    TreeNode rotateLeft(TreeNode x) {
        TreeNode y = x.right;
        TreeNode T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = newHeight(x);
        y.height = newHeight(y);

        return y;
    }

    // AVL ağacına düğüm ekleme metodu
    TreeNode insert(TreeNode node, String data) {
        if (node == null)
            return new TreeNode(data);

        if (data.compareTo(node.data) < 0)
            node.left = insert(node.left, data);
        else if (data.compareTo(node.data) > 0)
            node.right = insert(node.right, data);
        else
            return node; // Aynı düğümü birden fazla eklemeyi önle

        // Yükseklik güncellemesi
        node.height = newHeight(node);

        // Dengeliğin kontrolü
        int balance = balanceFactor(node);

        // Sol-sol durumu
        if (balance > 1 && data.compareTo(node.left.data) < 0)
            return rotateRight(node);

        // Sağ-sağ durumu
        if (balance < -1 && data.compareTo(node.right.data) > 0)
            return rotateLeft(node);

        // Sol-sağ durumu
        if (balance > 1 && data.compareTo(node.left.data) > 0) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        // Sağ-sol durumu
        if (balance < -1 && data.compareTo(node.right.data) < 0) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    // Ağacı dolaşarak yemek malzemelerini  yazdıran metot (inorder)
    void printIngredients(TreeNode node) {
        if (node != null) {
            printIngredients(node.left);
            System.out.println(node.data);
            printIngredients(node.right);
        }
    }

    // Yemek malzemelerini yazdıran metot
    void printIngredients() {
        printIngredients(root);
    }
}

// Tarifleri aşama aşama yazdıran metotların olduğu sınıf
public class PrintSteps {

    //VAGETERIAN 

    
    public static void patatasBravasSteps() {
        Queue<String> patatasBravasRecipeSteps = new LinkedList<>();
    
        patatasBravasRecipeSteps.offer("Boil 4 or 5 potatoes, which you cut into cubes beforehand, in hot water.");
        patatasBravasRecipeSteps.offer("Saute 1 chopped onion for the sauce.");
        patatasBravasRecipeSteps.offer("Add 1 piece of garlic, 1 tablespoon chili powder, and ½ cup tomato puree and fry for 5 more minutes.");
        patatasBravasRecipeSteps.offer("Put the boiled potatoes in a pan. Cook, stirring occasionally, until they turn golden brown.");
        patatasBravasRecipeSteps.offer("Place the browned potatoes on a serving plate.");
        patatasBravasRecipeSteps.offer("Pass the sauce through a blender.");
        patatasBravasRecipeSteps.offer("After the sauce has cooled, add the vinegar and mayonnaise and mix.");
        patatasBravasRecipeSteps.offer("Add the sauce over the potatoes and serve. Your Patatas Bravas recipe is ready. Enjoy your meal!");
    
        // Printing the steps
        int stepNumber = 1;
        while (!patatasBravasRecipeSteps.isEmpty()) {
            System.out.println("Step " + stepNumber + ": " + patatasBravasRecipeSteps.poll());
            stepNumber++;
        }
    }
    

    public static void ratatouilleSteps() {
        Queue<String> ratatouilleRecipeSteps = new LinkedList<>();
    
        ratatouilleRecipeSteps.offer("For the ratatouille recipe, first roast 1 capia pepper by placing a sheet pan on the stove. Then, while the pepper is still hot, cover it with cling film to preserve its flavor and place it on a plate.");
        ratatouilleRecipeSteps.offer("Add 2 tomatoes to the food processor and puree them. Peel the roasted red pepper, add it to the food processor, and blend with the tomatoes until you get a smooth consistency.");
        ratatouilleRecipeSteps.offer("Put 2 tablespoons of olive oil and ½ finely chopped onion in a pan. Start roasting slowly over low heat. Optionally, you can continue the roasting process by adding 2 pieces of garlic.");
        ratatouilleRecipeSteps.offer("Add the tomato sauce to the softened onions. Add 1 tablespoon granulated sugar, 1 tablespoon salt, 1/2 tablespoon black pepper, and 1 tablespoon fresh thyme and cook over low heat for 10-15 minutes.");
        ratatouilleRecipeSteps.offer("Remove the thyme from the slightly thickened sauce and spread the sauce on the bottom of an ovenproof dish.");
        ratatouilleRecipeSteps.offer("Slice 2 eggplants with their skins into thin and round slices. Soak them in salted water for 15 minutes to remove the bitter water. Then drain the water.");
        ratatouilleRecipeSteps.offer("Slice 2 zucchini thinly. Cut 1 tomato into the same size and shape. Put all the vegetables in a bowl. Add ½ tea cup of olive oil, 1 tablespoon salt, and other spices.");
        ratatouilleRecipeSteps.offer("In the bowl where you spread the tomato sauce, arrange your vegetables side by side from the outside to the inside. Drizzle olive oil over them. Add a few fresh thyme leaves on top.");
        ratatouilleRecipeSteps.offer("Cover with aluminum foil and make 4-5 holes with a knife. Bake in a preheated 190-degree oven for 20 minutes.");
        ratatouilleRecipeSteps.offer("After 20 minutes, remove the foil and bake for another 30 minutes.");
        ratatouilleRecipeSteps.offer("The lightly browned ratatouille is ready. Enjoy your meal!");
    
        // Printing the steps
        int stepNumber = 1;
        while (!ratatouilleRecipeSteps.isEmpty()) {
            System.out.println("Step " + stepNumber + ": " + ratatouilleRecipeSteps.poll());
            stepNumber++;
        }
    }
    

    public static void mucverSteps() {
        Queue<String> mucverRecipeSteps = new LinkedList<>();
    
        mucverRecipeSteps.offer("For the hash browns, grate 3 zucchini and 1 carrot. Ensure the zucchini is washed thoroughly in plenty of water. Use the coarse part of the grater. To prevent dilution of the hash browns, squeeze the juice out of the grated zucchini.");
        mucverRecipeSteps.offer("In a large bowl, combine the grated zucchini and carrot with 2 beaten eggs, 6 pieces of chopped spring onions, ½ bunch chopped dill, 1.5 teaspoons salt, and 1 teaspoon black pepper.");
        mucverRecipeSteps.offer("Add 1 tea glass of flour to the mixture and blend all the ingredients together until well combined.");
        mucverRecipeSteps.offer("Mix with a spatula until all the ingredients form a paste-like consistency.");
        mucverRecipeSteps.offer("Adjust each hash brown to approximately 1 heaping tablespoon and pour into a pan.");
        mucverRecipeSteps.offer("Fry the hash browns in hot oil until they are golden brown on both sides.");
        mucverRecipeSteps.offer("After frying, drain the excess oil from the hash browns by placing them on a paper towel.");
        mucverRecipeSteps.offer("Serve the zucchini hash browns on a serving plate and enjoy them with whipped yogurt.");
    
        // Printing the steps
        int stepNumber = 1;
        while (!mucverRecipeSteps.isEmpty()) {
            System.out.println("Step " + stepNumber + ": " + mucverRecipeSteps.poll());
            stepNumber++;
        }
    }
    



    public static void falafelSteps() {
        Queue<String> falafelRecipeSteps = new LinkedList<>();
    
        falafelRecipeSteps.offer("Drain 1.5 cups of chickpeas that you soaked in water the night before and pour them into a food processor. (Chickpeas will not be boiled.)");
        falafelRecipeSteps.offer("Add 1 onion, 2 cloves of garlic, 1/4 bunch of parsley, and 2 spring onions to the chickpeas and run the food processor once.");
        falafelRecipeSteps.offer("After the food processor has extracted the ingredients, mix them together a little with a spatula.");
        falafelRecipeSteps.offer("Then add 1 teaspoon salt, 1 teaspoon black pepper, 1 teaspoon coriander, 1 teaspoon cumin, 1 teaspoon paprika, and 1 teaspoon baking powder. Process in a food processor until no coarse grains remain.");
        falafelRecipeSteps.offer("Put the prepared mixture in a deep bowl and add 2 tablespoons of flour. Do not knead at this stage. Leave the falafel mixture in the refrigerator for at least 1 hour to rest.");
        falafelRecipeSteps.offer("Take the falafel mixture out of the refrigerator and roll it with your hands by taking pieces smaller than a walnut.");
        falafelRecipeSteps.offer("When your falafel patties are ready, fry them in well-heated oil.");
        falafelRecipeSteps.offer("When frying the falafels, make sure to fry them over medium heat so that they cook well inside.");
        falafelRecipeSteps.offer("For the sauce, add 2 cloves of garlic, 2 tablespoons of tahini, and 1 tablespoon of lemon juice into the strained yogurt. Mix well.");
        falafelRecipeSteps.offer("Serve the fried falafel patties with the prepared sauce. Enjoy!");
    
        // Printing the steps
        int stepNumber = 1;
        while (!falafelRecipeSteps.isEmpty()) {
            System.out.println("Step " + stepNumber + ": " + falafelRecipeSteps.poll());
            stepNumber++;
        }
    }
    



    public static void celeryWithOrangeSauceSteps() {
        Queue<String> celeryRecipeSteps = new LinkedList<>();
    
        celeryRecipeSteps.offer("Peel 2 celery root and cut it into cubes. To prevent discoloration, soak the celery in lemon juice.");
        celeryRecipeSteps.offer("Finely chop 1 onion. Slice 1 carrot thinly and sauté it with the onion in a pot where you have added 6 tablespoons of olive oil.");
        celeryRecipeSteps.offer("Then add the potatoes on top.");
        celeryRecipeSteps.offer("Add the celery and sauté for a while.");
        celeryRecipeSteps.offer("Sprinkle 1 teaspoon flour over the vegetables and stir.");
        celeryRecipeSteps.offer("Then add 1 teaspoon sugar, ½ teaspoon salt, 1 glass of orange juice, ½ glass lemon juice, and 1 glass of water.");
        celeryRecipeSteps.offer("Finally, place the celery stalk in the middle of the pot and cover it with a lid. Once it boils, simmer on low heat for about 25-35 minutes or until the vegetables are tender.");
        celeryRecipeSteps.offer("After cooking, let the dish rest before serving. Enjoy your meal!");
    
        // Printing the steps
        int stepNumber = 1;
        while (!celeryRecipeSteps.isEmpty()) {
            System.out.println("Step " + stepNumber + ": " + celeryRecipeSteps.poll());
            stepNumber++;
        }
    }
    



    // TATLILAR 

    public static void halvaSteps() {
        Queue<String> halvaRecipeSteps = new LinkedList<>();
    
        halvaRecipeSteps.offer("Take 1.5 cups of sugar and 1.5 cups of water in a bowl and mix until the sugar dissolves. Keep it aside for later use.");
        halvaRecipeSteps.offer("First, put 125 grams of oil into the pot to be cooked and melt it.");
        halvaRecipeSteps.offer("Then add 2 cups of flour and a handful of walnuts.");
        halvaRecipeSteps.offer("Roast over medium heat, stirring constantly, for 10-15 minutes until its color changes.");
        halvaRecipeSteps.offer("Turn the heat down to very low.");
        halvaRecipeSteps.offer("Pour the cold syrup prepared earlier over the roasted flour and walnuts, stirring constantly.");
        halvaRecipeSteps.offer("At first, it will have a very watery consistency, but as you mix and heat, the flour will absorb the syrup.");
        halvaRecipeSteps.offer("When the halva reaches a good consistency, turn off the stove.");
        halvaRecipeSteps.offer("You can cover it and let it brew for about 5 minutes, or you can shape it immediately while it's hot.");
    
        // Printing the steps
        int stepNumber = 1;
        while (!halvaRecipeSteps.isEmpty()) {
            System.out.println("Step " + stepNumber + ": " + halvaRecipeSteps.poll());
            stepNumber++;
        }
    }
    



    public static void magnoliaSteps() {
        Queue<String> magnoliaRecipeSteps = new LinkedList<>();
    
        magnoliaRecipeSteps.offer("For Magnolia's pudding, take 5 cups of milk, 3/4 cup of granulated sugar, 4 tablespoons of flour, 1 egg and half a box of liquid cream in a suitable pot.");
        magnoliaRecipeSteps.offer("Start cooking by mixing the ingredients with a wire whisk.");
        magnoliaRecipeSteps.offer("Cook, stirring, until the pudding thickens and thickens, and then turn off the stove.");
        magnoliaRecipeSteps.offer("Add 1 pack of vanilla and mix it well, then put the pudding into a suitable mixing bowl.");
        magnoliaRecipeSteps.offer("While the pudding is waiting, cover it with stretch film to prevent it from crusting.");
        magnoliaRecipeSteps.offer("Put 1 pack of baby biscuits and 1 glass of roasted hazelnuts into the food processor and quickly blend them.");
        magnoliaRecipeSteps.offer("Beat the cooled pudding with a mixer for about two minutes.");
        magnoliaRecipeSteps.offer("Now you can start filling the dessert into the cups in which you will present it.");
        magnoliaRecipeSteps.offer("First, place the hazelnut biscuit mixture on the bottom of the cups. Smooth it with the back of a spoon for a better look.");
        magnoliaRecipeSteps.offer("Then, place sliced strawberries on the edges of the cups.");
        magnoliaRecipeSteps.offer("After arranging the strawberries, divide the pudding into the cups. Smooth the top of the pudding with the back of the spoon.");
        magnoliaRecipeSteps.offer("Finally, place the hazelnut biscuit mixture prepared earlier on top of the pudding. Decorate the magnolia with sliced strawberries.");
        magnoliaRecipeSteps.offer("After letting the desserts rest in the refrigerator for at least 2-3 hours, your strawberry magnolias are ready to serve. Enjoy your meal!");
    
        // Printing the steps
        int stepNumber = 1;
        while (!magnoliaRecipeSteps.isEmpty()) {
            System.out.println("Step " + stepNumber + ": " + magnoliaRecipeSteps.poll());
            stepNumber++;
        }
    }
    





    public static void sanSebastianCheesecakeSteps() {
        Queue<String> cheesecakeRecipeSteps = new LinkedList<>();
    
        cheesecakeRecipeSteps.offer("Preheat the oven to 325°F (165°C). Grease a 9-inch springform pan.");
        cheesecakeRecipeSteps.offer("In a medium bowl, combine 150 grams of graham cracker crumbs, 1/4 cup sugar, and 1/2 cup melted butter.");
        cheesecakeRecipeSteps.offer("Press into the bottom of the prepared pan.");
        cheesecakeRecipeSteps.offer("In a large bowl, mix 4 packs of cream cheese with 250 grams of sugar until smooth.");
        cheesecakeRecipeSteps.offer("Mix 4 eggs one by one.");
        cheesecakeRecipeSteps.offer("Mix 160 ml sour cream, 160 ml heavy cream and 1 tablespoon vanilla.");
        cheesecakeRecipeSteps.offer("Pour the filling over the dough.");
        cheesecakeRecipeSteps.offer("Bake for 60 minutes or until the center is set.");
        cheesecakeRecipeSteps.offer("Cool, then refrigerate overnight before removing from pan.");
    
        // Printing the steps
        int stepNumber = 1;
        while (!cheesecakeRecipeSteps.isEmpty()) {
            System.out.println("Step " + stepNumber + ": " + cheesecakeRecipeSteps.poll());
            stepNumber++;
        }
    }
    

    public static void sutlacSteps() {
        Queue<String> sutlacRecipeSteps = new LinkedList<>();
    
        sutlacRecipeSteps.offer("Wash 2 tea glasses of rice and put it on the fire with water.");
        sutlacRecipeSteps.offer("Boil until the rice stretches and absorbs the water. Be careful not to burn the rice by turning the stove on too high a heat.");
        sutlacRecipeSteps.offer("Then add 1 liter of cold milk. Stir it 1-2 times and wait for it to boil.");
        sutlacRecipeSteps.offer("Meanwhile, in a bowl, crush 3 tablespoons of rice flour with 1 glass of cold milk. Make sure that there are no lumps left.");
        sutlacRecipeSteps.offer("Take 1-2 scoops of boiling milk in the pot and add it to the bowl. (rice flour should be warm).");
        sutlacRecipeSteps.offer("Add the rice flour to the pot and cook for 10 minutes, stirring occasionally.");
        sutlacRecipeSteps.offer("Add 2 cups of granulated sugar, stir and boil.");
        sutlacRecipeSteps.offer("Divide the rice pudding into bowls.");
        sutlacRecipeSteps.offer("Once the rice pudding has cooled down, you can serve it by sprinkling as much cinnamon as you like. Enjoy your meal.");
    
        // Printing the steps
        int stepNumber = 1;
        while (!sutlacRecipeSteps.isEmpty()) {
            System.out.println("Step " + stepNumber + ": " + sutlacRecipeSteps.poll());
            stepNumber++;
        }
    }
    



    public static void chocolateChipCookiesSteps() {
        Queue<String> cookiesRecipeSteps = new LinkedList<>();
    
        cookiesRecipeSteps.offer("Preheat your oven to 350°F (175°C).");
        cookiesRecipeSteps.offer("In a large bowl, cream 1 cup each butter, white sugar, and brown sugar until smooth.");
        cookiesRecipeSteps.offer("Beat 2 eggs one by one, then add 1 teaspoon of vanilla and mix.");
        cookiesRecipeSteps.offer("Dissolve 1 teaspoon of baking soda in hot water. Add it to the dough with half a teaspoon of salt.");
        cookiesRecipeSteps.offer("Mix 3 cups of flour and 340 grams of chocolate chips.");
        cookiesRecipeSteps.offer("Pour large spoonfuls into ungreased pans.");
        cookiesRecipeSteps.offer("Bake in the preheated oven for about 10 minutes or until the edges are nicely browned.");
    
        // Printing the steps
        int stepNumber = 1;
        while (!cookiesRecipeSteps.isEmpty()) {
            System.out.println("Step " + stepNumber + ": " + cookiesRecipeSteps.poll());
            stepNumber++;
        }
    }
    


       //ZEYTİN YAĞLILAR 

    public static void spinachWithOliveOilSteps() {
        Queue<String> spinachRecipeSteps = new LinkedList<>();
    
        spinachRecipeSteps.offer("Wash 500 grams of fresh spinach leaves thoroughly and drain excess water.");
        spinachRecipeSteps.offer("Heat 1/4 cup olive oil in a large pot over medium heat.");
        spinachRecipeSteps.offer("Add 1 finely chopped onion and 3 crushed cloves of garlic. Cook until the onions soften and become transparent.");
        spinachRecipeSteps.offer("Add 2 tablespoons of pine nuts to the pot and roast for a few minutes.");
        spinachRecipeSteps.offer("Add washed spinach leaves, 1 teaspoon of dried dill, juice of 1 lemon, 1 teaspoon of salt and black pepper.");
        spinachRecipeSteps.offer("Cook, stirring occasionally, until the spinach becomes soft.");
        spinachRecipeSteps.offer("After the spinach is cooked, remove it from the stove. You can add salt and pepper if necessary.");
        spinachRecipeSteps.offer("You can serve spinach with olive oil hot or at room temperature.");
    
        // Printing the steps
        int stepNumber = 1;
        while (!spinachRecipeSteps.isEmpty()) {
            System.out.println("Step " + stepNumber + ": " + spinachRecipeSteps.poll());
            stepNumber++;
        }
    }




    public static void cauliflowerWithOliveOilSteps() {
        Queue<String> cauliflowerRecipeSteps = new LinkedList<>();
    
        cauliflowerRecipeSteps.offer("Heat 1/4 cup olive oil in a large skillet over medium heat.");
        cauliflowerRecipeSteps.offer("Add 1 finely chopped onion and 2 crushed cloves of garlic. Cook until the onions soften and become transparent.");
        cauliflowerRecipeSteps.offer("Add 2 medium-sized carrots, diced into cubes, to the pan. Cook, stirring, until the carrots soften a little.");
        cauliflowerRecipeSteps.offer("Add 1 medium sized Cauliflower florets and 2 chopped tomatoes. Season with 1 teaspoon of ground pepper, salt and black pepper.");
        cauliflowerRecipeSteps.offer("Cover the pan and cook until the cauliflower becomes soft, check by stirring occasionally. You can add some water to prevent sticking if necessary.");
        cauliflowerRecipeSteps.offer("When the cauliflower is cooked, remove it from the stove.");
        cauliflowerRecipeSteps.offer("Sprinkle chopped parsley on top before placing it on a serving plate.");
    
        // Printing the steps
        int stepNumber = 1;
        while (!cauliflowerRecipeSteps.isEmpty()) {
            System.out.println("Step " + stepNumber + ": " + cauliflowerRecipeSteps.poll());
            stepNumber++;
        }
    }
    




    public static void zucchiniStewSteps() {
        Queue<String> zucchiniStewRecipeSteps = new LinkedList<>();
    
        zucchiniStewRecipeSteps.offer("Wash the zucchinis well and peel them in various ways. Chop 4 zucchinis into pieces slightly larger than cubes.");
        zucchiniStewRecipeSteps.offer("Chop 1 Onion into small pieces. Chop 1 large tomato into cubes.");
        zucchiniStewRecipeSteps.offer("Put 4 tablespoons of oil in a wide-based pot and heat it. Then start frying the onions.");
        zucchiniStewRecipeSteps.offer("When the onions change color, add 1 teaspoon of tomato paste and continue frying with the tomato paste.");
        zucchiniStewRecipeSteps.offer("Add the zucchinis, mix and cook for a while.");
        zucchiniStewRecipeSteps.offer("Then add tomatoes, 1 teaspoon of sugar and 1 spoon of spices, mix.");
        zucchiniStewRecipeSteps.offer("Cook on low heat for about 15-20 minutes until the zucchinis are tender, do not forget to open the lid and stir in between.");
        zucchiniStewRecipeSteps.offer("Remove from the stove, sprinkle with finely chopped dill and serve. Enjoy your meal.");
    
        // Printing the steps
        int stepNumber = 1;
        while (!zucchiniStewRecipeSteps.isEmpty()) {
            System.out.println("Step " + stepNumber + ": " + zucchiniStewRecipeSteps.poll());
            stepNumber++;
        }
    }
    





    public static void artichokeWithOliveOilSteps() {
        Queue<String> artichokeRecipeSteps = new LinkedList<>();
    
        artichokeRecipeSteps.offer("In a pan, fry half a glass of olive oil and 1 diced onion until it turns slightly pink.");
        artichokeRecipeSteps.offer("Add 1 diced and boiled potato and a large carrot each and fry for 2-3 minutes.");
        artichokeRecipeSteps.offer("After adding 1 cup of peas, remove your garnish from the stove 3-4 minutes later.");
        artichokeRecipeSteps.offer("To prepare the sauce in which you will cook the artichokes, mix the juice of 2 squeezed lemons, 1 glass of water, 1 teaspoon of granulated sugar, 1 teaspoon of salt and 2 tablespoons of olive oil in a bowl.");
        artichokeRecipeSteps.offer("Place 8 artichokes in a large pot and top them with the garnish you prepared.");
        artichokeRecipeSteps.offer("Transfer the lemon water mixture into the pot.");
        artichokeRecipeSteps.offer("Then close the lid and cook until the artichokes soften, your meal is ready.");
    
        // Printing the steps
        int stepNumber = 1;
        while (!artichokeRecipeSteps.isEmpty()) {
            System.out.println("Step " + stepNumber + ": " + artichokeRecipeSteps.poll());
            stepNumber++;
        }
    }
    

    public static void sarmaSteps() {
        Queue<String> sarmaRecipeSteps = new LinkedList<>();
    
        sarmaRecipeSteps.offer("To prepare the stuffing of the stuffing; Heat half a glass of olive oil in a wide-based pot.");
        sarmaRecipeSteps.offer("Fry 3 grated onions until they turn light brown.");
        sarmaRecipeSteps.offer("Then add 1.5 tablespoons of pine nuts and continue roasting.");
        sarmaRecipeSteps.offer("Roast 1.5 cups of rice, which you have soaked in warm water for about 5 minutes and then drained, together with the onions until the rice becomes transparent.");
        sarmaRecipeSteps.offer("Then respectively; Add 1 tablespoon of currants, 1 teaspoon each of salt, black pepper, allspice and cinnamon and mix.");
        sarmaRecipeSteps.offer("Add about 1 glass of hot water and cook the stuffing on low heat for 5 minutes, then remove it from the stove.");
        sarmaRecipeSteps.offer("Open the pickled vine leaves on the counter, leaving the veined parts on top. In the middle part of each leaf; Prepare and share a teaspoon of the warmed stuffing.");
        sarmaRecipeSteps.offer("Bring the edges in and move from the wide part to the tip. Wrap all the leaves tightly.");
        sarmaRecipeSteps.offer("Cover the bottom of the sarma pot with a few vine leaves.");
        sarmaRecipeSteps.offer("Arrange the stuffed wraps in a row, side by side. After slicing the lemon into rings, place it on the wraps.");
        sarmaRecipeSteps.offer("To prevent them from opening during cooking; Cover them with a flat serving plate.");
        sarmaRecipeSteps.offer("After adding 1.5 cups of hot water and 2 tablespoons of olive oil to the pot, cook the stuffed wraps over low heat for about 35 minutes.");
        sarmaRecipeSteps.offer("According to desire; Serve warm or cold.");
    
        // Printing the steps
        int stepNumber = 1;
        while (!sarmaRecipeSteps.isEmpty()) {
            System.out.println("Step " + stepNumber + ": " + sarmaRecipeSteps.poll());
            stepNumber++;
        }
    }







         // ETLİ YEMEKLER



    public static void karnıyarıkSteps(){

        Queue <String> karnıyarıkRecipeSteps = new LinkedList<>();

        karnıyarıkRecipeSteps.offer("Wash the eggplants in plenty of water, peel them in pajamas and keep them in salt water.");
        karnıyarıkRecipeSteps.offer("Heat 3 tablespoons of olive oil in a pan.");
        karnıyarıkRecipeSteps.offer("Add 1 chopped onion and fry until it turns pink.");
        karnıyarıkRecipeSteps.offer("Add 2 chopped green peppers and continue roasting.");
        karnıyarıkRecipeSteps.offer("Add 350 grams of medium-fat ground meat and cook it with the roasted onions until it takes color and absorbs its water.");
        karnıyarıkRecipeSteps.offer("Stirring in between and continuing the cooking process, respectively; Add 2 cloves of garlic, half a teaspoon of tomato paste, half a teaspoon of pepper paste, 1 teaspoon each of salt and black pepper.");
        karnıyarıkRecipeSteps.offer("Add 2 diced tomatoes, cook for about 5 minutes, then reduce the heat and add a handful of chopped parsley, stir one last time and remove from the stove.");
        karnıyarıkRecipeSteps.offer("Fry 6 eggplants that you have peeled and put away in pajamas.");
        karnıyarıkRecipeSteps.offer("Place in an ovenproof dish.");
        karnıyarıkRecipeSteps.offer("Cut the eggplants in half from the middle.");
        karnıyarıkRecipeSteps.offer("Add plenty of the stuffing you prepared.");
        karnıyarıkRecipeSteps.offer("Add cherry tomatoes and pepper slices on the eggplants.");
        karnıyarıkRecipeSteps.offer("After cooking the karnıyarık in a preheated oven at 170 degrees for 20-25 minutes, serve it hot, accompanied by buttered rice pilaf if you wish.");

         //aşamaları yazdırma döngüsü

         int stepNumber=1;
         while(!karnıyarıkRecipeSteps.isEmpty()){
            System.out.println("Step "+ stepNumber+ ": "+ karnıyarıkRecipeSteps.poll());
            stepNumber++;
         }



    }


       public static void mantıSteps(){


        Queue <String> mantıRecipeSteps = new LinkedList<>();
        mantıRecipeSteps.offer("Pour 3 cups of flour into a kneading bowl, make a well in the middle, add 1 egg, 1 teaspoon of salt and 1 cup of warm water and knead.");
        mantıRecipeSteps.offer("When you obtain a flexible dough that does not stick to the hand, cover it and leave it to rest.");
        mantıRecipeSteps.offer("For the filling, grate 1 onion. Add 250 grams of minced meat to the onion.");
        mantıRecipeSteps.offer("Then, add half a teaspoon of black pepper, a teaspoon of salt and half a teaspoon of chili pepper and knead well.");
        mantıRecipeSteps.offer("Divide the dough into pieces.");
        mantıRecipeSteps.offer("Roll out each meringue with a rolling pin, slightly thicker than the ready-made phyllo dough.");
        mantıRecipeSteps.offer("Cut the rolled dough into squares.");
        mantıRecipeSteps.offer("Place a small piece of the minced meat mixture in the middle of each square. Close it in a bundle shape.");
        mantıRecipeSteps.offer("Add water to a deep pot, bring it to a boil and cook the manti for about 20 minutes.");
        mantıRecipeSteps.offer("For the sauce, melt 2 tablespoons of butter in a bowl.");
        mantıRecipeSteps.offer("Add 2 tablespoons of tomato paste to the melted butter and fry.");
        mantıRecipeSteps.offer("Add the water from the cooked manti and the tomato paste and boil for a few minutes.");
        mantıRecipeSteps.offer("After draining the manti and placing it on a serving plate, pour 1 bowl of garlic yoghurt over it. ");
        mantıRecipeSteps.offer("After pouring the sauce you prepared on it, you can serve it by adding 1 teaspoon of dried mint, half a teaspoon of sumac and 1 teaspoon of chili pepper.");
        
        
        //aşamaları yazdırma döngüsü 

        int stepNumber=1;
        while(!mantıRecipeSteps.isEmpty()){
            System.out.println("Steps " + stepNumber+ ": "+ mantıRecipeSteps.poll());
            stepNumber++;
        }

    }

      public static void whiteBeanStewWithMeatStepş(){
            Queue <String> whiteBeanStewRecipeSteps = new LinkedList<>();

            whiteBeanStewRecipeSteps.offer("1.5 cups of dried beans are placed in hot water and soaked the night before.");
            whiteBeanStewRecipeSteps.offer("Chop 1 onion and fry the onions in a pressure cooker with 5 spoons of oil.");
            whiteBeanStewRecipeSteps.offer("Add 300 grams of diced meat and fry lightly. At this stage, the meat will release and absorb its water.");
            whiteBeanStewRecipeSteps.offer("After adding 1 tablespoon of pepper paste, 1 tablespoon of tomato paste, 1 teaspoon of salt, 1 teaspoon of chili pepper, the drained beans are added.");
            whiteBeanStewRecipeSteps.offer("Add enough hot water to cover it and close the pressure cooker lid.");
            whiteBeanStewRecipeSteps.offer("After it boils, turn it to low heat and cook for 30-40 minutes, your meal is ready.");
            

            //aşamaları yazdırma döngüsü

            int stepNumber=1;
            while(!whiteBeanStewRecipeSteps.isEmpty()){
                System.out.println("Steps " + stepNumber+ ": "+ whiteBeanStewRecipeSteps.poll());
                stepNumber++;
            }

      }






    public static void patlıcanOturtmaSteps(){
            Queue <String> patlıcanOturtmaRecipeSteps = new LinkedList<>();


patlıcanOturtmaRecipeSteps.offer("Cut 3 large eggplants into thick rings, soak them in salt water, then fry both sides.");
patlıcanOturtmaRecipeSteps.offer("Then, lightly sauté 3-4 tablespoons of oil, 1 onion and 2 chopped peppers in the pan.");
patlıcanOturtmaRecipeSteps.offer("Add 200 grams of minced meat and fry until the minced meat is completely tender.");
patlıcanOturtmaRecipeSteps.offer("Finally, add 2 grated tomatoes, 1-2 cloves of garlic and 1 teaspoon of all the spices, stir and fry until the water is absorbed.");
patlıcanOturtmaRecipeSteps.offer("Just before removing it from the stove, add the chopped parsley, stir and remove it from the stove.");
patlıcanOturtmaRecipeSteps.offer("Fill the middle of the fried eggplants with stuffing.");
patlıcanOturtmaRecipeSteps.offer("After all the eggplants are stuffed, make a sauce with 2 tablespoons of tomato paste and 3 glasses of water and put them in the oven.");
patlıcanOturtmaRecipeSteps.offer("You can bake it in the oven at 200 degrees.");
patlıcanOturtmaRecipeSteps.offer("If you wish, you can put cheddar on top, let it melt, and serve when it is about to come out of the oven.");

            //aşamaları yazdırma döngüsü

            int stepNumber=1;
            while(!patlıcanOturtmaRecipeSteps.isEmpty()){
                System.out.println("Steps " + stepNumber+ ": "+ patlıcanOturtmaRecipeSteps.poll());
                stepNumber++;
            }



    }





    public static void sauteedMeatSteps(){
    Queue <String> sauteedMeatRecipeSteps = new LinkedList<>();

    //et sotenin steplerini linked liste ekliyoruz

        sauteedMeatRecipeSteps.offer("First add 3 tablespoons of oil and 1 tablespoon of butter to the pan.");
        sauteedMeatRecipeSteps.offer("Then add 1 chopped onion. Start roasting.");
        sauteedMeatRecipeSteps.offer("Add 2 chopped capias and 2 chopped green peppers and continue roasting.");
        sauteedMeatRecipeSteps.offer("Add 400 grams of cubed beef on top and continue mixing.");
        sauteedMeatRecipeSteps.offer("Add 2 chopped tomatoes on top.");
        sauteedMeatRecipeSteps.offer("Add 2 teaspoons of salt, half a teaspoon of black pepper, half a teaspoon of cumin, 1 teaspoon of thyme, and 1 teaspoon of chili pepper.");
        sauteedMeatRecipeSteps.offer("Add 1 tablespoon of pepper paste.");
        sauteedMeatRecipeSteps.offer("Keep it on high heat until the tomatoes release their juices.");
        sauteedMeatRecipeSteps.offer("Then cook over low heat until it brews, your meal is ready.");

         //Aşamaları yazdırma döngüsü

         int stepNumber=1;
         while(!sauteedMeatRecipeSteps.isEmpty()){
             System.out.println("Step "+ stepNumber + ": "+ sauteedMeatRecipeSteps.poll());
             stepNumber++;
         }
    }
         // ÇORBALAR 


    public static void broccoliSoupSteps(){

        Queue <String> broccoliSoupRecipeSteps = new LinkedList<>();

        //brokoli çorbasının steplerini linked liste ekliyoruz

        broccoliSoupRecipeSteps.offer("Boil 500 grams of broccoli, cut into small pieces, with 7 glasses of water.");
        broccoliSoupRecipeSteps.offer("After the broccoli is boiled, remove it with the help of a colander and set the water aside.");
        broccoliSoupRecipeSteps.offer("Then, melt 1 tablespoon of olive oil and 1 tablespoon of butter in a deep pot.");
        broccoliSoupRecipeSteps.offer("Add a tablespoon of flour and fry until its smell disappears and it turns a light color.");
        broccoliSoupRecipeSteps.offer("Add the water in which you boiled the broccoli to the roasted flour, little by little, and stir constantly with a whisk to avoid lumps.");
        broccoliSoupRecipeSteps.offer("After boiling for 2-3 minutes, add the boiled and drained broccoli into the reserved water.");
        broccoliSoupRecipeSteps.offer("Blend the soup with a hand blender to get a smooth consistency.");
        broccoliSoupRecipeSteps.offer("Finally, add 1.5 teaspoons of salt and half a teaspoon of black pepper and mix.");
        broccoliSoupRecipeSteps.offer("Bring the broccoli soup to a boil, turn off the heat and serve hot.");

        //Aşamaları yazdırma döngüsü

        int stepNumber=1;
        while(!broccoliSoupRecipeSteps.isEmpty()){
            System.out.println("Step "+ stepNumber + ": "+ broccoliSoupRecipeSteps.poll());
            stepNumber++;
        }
    }


    public static void tarhanaSoupSteps(){

        Queue <String> tarhanaSoupRecipeSteps = new LinkedList<>();
         
        //tarhana çorbasının steplerini linked liste ekliyoruz

        tarhanaSoupRecipeSteps.offer("3 tablespoons of tarhana are kept in a bowl with 1 glass of water for half an hour and then crushed.");
        tarhanaSoupRecipeSteps.offer("Add 2 tablespoons of oil to the pot and Roast 1 tablespoon of tomato paste until its smell disappears.");
        tarhanaSoupRecipeSteps.offer("Add 1 teaspoon of mint and 5 more glasses of water to the tarhana soaked in water.");
        tarhanaSoupRecipeSteps.offer("Add 1 teaspoon of salt and boil, stirring constantly, until it thickens. The soup is ready.");


        //Aşamaları yazdırma döngüsü
        int stepNumber=1;
        while(!tarhanaSoupRecipeSteps.isEmpty()){
            System.out.println("Step "+ stepNumber + ": "+ tarhanaSoupRecipeSteps.poll());
            stepNumber++;
        }

    }




    public static void ezogelinSoupSteps(){
    Queue <String> ezogelinSoupRecipeSteps = new LinkedList<>();

    // ezogelin çorbasının steplerini linked liste ekliyoruz

    ezogelinSoupRecipeSteps.offer("Bring hot water to a boil in a large pot. Transfer 2 tea glasses of washed and drained red lentils into the pot.");
    ezogelinSoupRecipeSteps.offer("Transfer 3 tablespoons of rice, 2 tablespoons of bulgur and 1 teaspoon of salt into the pot.");
    ezogelinSoupRecipeSteps.offer("Cook for about 35 minutes, until the red lentils are soft.");
    ezogelinSoupRecipeSteps.offer("Melt 1 tablespoon of butter in a separate pan.");
    ezogelinSoupRecipeSteps.offer("Add 1 diced onion and start roasting.");
    ezogelinSoupRecipeSteps.offer("Add 1 teaspoon of tomato paste to the roasted onions.");
    ezogelinSoupRecipeSteps.offer("Add 1 teaspoon of mint and fry for 2 more minutes.");
    ezogelinSoupRecipeSteps.offer("Remove your roasted sauce from the stove.");
    ezogelinSoupRecipeSteps.offer("Add the roasted tomato paste and onion mixture to the pot in which you boiled the legumes.");
    ezogelinSoupRecipeSteps.offer("Then quickly stir the soup and cook for another 10 minutes, your soup is ready.");

    //Aşamaları yazdırma döngüsü
    int stepNumber = 1;
        while(!ezogelinSoupRecipeSteps.isEmpty()){
            System.out.println("Step "+ stepNumber + ": " + ezogelinSoupRecipeSteps.poll());
            stepNumber++;

        }


    }
    public static void yoghurtSoupSteps(){

        //yoğurt çorbasının steplerini linked liste ekliyoruz

        Queue <String> yoghurtSoupRecipeSteps =new LinkedList<>();

        yoghurtSoupRecipeSteps.offer("Add 2 cups of yoghurt, 1.5 tablespoons of flour, 2 tablespoons of lemon juice to the pot in which you will cook the plateau soup and stir until it reaches a smooth consistency.");
        yoghurtSoupRecipeSteps.offer("Add 1 egg yolk to the mixture and mix again.");
        yoghurtSoupRecipeSteps.offer("Add 6 glasses of cold water and let it boil over medium heat.");
        yoghurtSoupRecipeSteps.offer("When the soup starts to boil, add 1 tea glass of washed and drained rice and let it boil again.");
        yoghurtSoupRecipeSteps.offer("Meanwhile, heat 2 tablespoons of olive oil and butter in a separate pan. ");
        yoghurtSoupRecipeSteps.offer("Add 1 tablespoon of dried mint, stir and remove from the stove. ");
        yoghurtSoupRecipeSteps.offer("Pour the prepared oil over the boiling soup. Add chili pepper and salt.");
        yoghurtSoupRecipeSteps.offer("Mix it well, turn off the stove, and it's ready to serve.");
           
        //Aşamaları yazdırma döngüsü
        int stepNumber = 1;
        while(!yoghurtSoupRecipeSteps.isEmpty()){
            System.out.println("Step "+ stepNumber + ": " + yoghurtSoupRecipeSteps.poll());
            stepNumber++;

        }
    
    
    }

    


    public static void lentilSoupPrintSteps(){
        Queue <String> lentilSoupRecipeSteps = new LinkedList<>();
    
        //mercimek çorbası tarifini linked liste ekliyoruz
    
        lentilSoupRecipeSteps.offer("Add 3 tablespoons of oil to a deep pot. Fry 1 large chopped onion with oil.");
        lentilSoupRecipeSteps.offer("Add 1 tablespoon of flour to the roasted onions and continue roasting until the smell disappears and the color changes. ");
        lentilSoupRecipeSteps.offer("Transfer one carrot and one potato, cut into large pieces, into the pot and continue mixing.");
        lentilSoupRecipeSteps.offer("After washing with salt, black pepper and plenty of water, add 1.5 cups of drained lentils and mix well one last time.");
        lentilSoupRecipeSteps.offer("Add 6 cups of hot water to the pot.");
        lentilSoupRecipeSteps.offer("Then close the lid and cook for about 40 minutes, stirring occasionally, until the potatoes and carrots become soft.");
        lentilSoupRecipeSteps.offer("In order for the soup to have a smooth consistency after cooking; pass it through a hand blender.");
        lentilSoupRecipeSteps.offer("After cooking for another 5 minutes, remove from the stove.");
        lentilSoupRecipeSteps.offer("Heat 3 tablespoons of oil and 2 tablespoons of butter in a pan. ");
        lentilSoupRecipeSteps.offer("Add 1 teaspoon of red pepper powder and heat the oil for 2 minutes, then remove it from the stove.");
        lentilSoupRecipeSteps.offer("Put the soup in a bowl, drizzle with the heated oil and serve.");
    
        //Aşamaları yazdırma döngüsü
    
        int stepNumber = 1;
        while(!lentilSoupRecipeSteps.isEmpty()){
            System.out.println("Step "+ stepNumber + ": " + lentilSoupRecipeSteps.poll());
            stepNumber++;

        }


    
    
    }
    
}

   
   

public class Main {
    public static void main(String[] args) {

            // Tüm malzemeleri yemeklerin AVL treelerine ekliyoruz
            AVLTree lentilSoupTree = new AVLTree();
            lentilSoupTree.root = lentilSoupTree.insert(lentilSoupTree.root, "sunflower oil");
            lentilSoupTree.root = lentilSoupTree.insert(lentilSoupTree.root, "onion (chopped coarsely)");
            lentilSoupTree.root = lentilSoupTree.insert(lentilSoupTree.root, "flour");
            lentilSoupTree.root = lentilSoupTree.insert(lentilSoupTree.root, "carrots (coarsely chopped)");
            lentilSoupTree.root = lentilSoupTree.insert(lentilSoupTree.root, "salt");
            lentilSoupTree.root = lentilSoupTree.insert(lentilSoupTree.root, "black pepper");
            lentilSoupTree.root = lentilSoupTree.insert(lentilSoupTree.root, "red or yellow lentils");
            lentilSoupTree.root = lentilSoupTree.insert(lentilSoupTree.root, "hot water");
            lentilSoupTree.root = lentilSoupTree.insert(lentilSoupTree.root, "liquid oil");
            lentilSoupTree.root = lentilSoupTree.insert(lentilSoupTree.root, "butter");
            lentilSoupTree.root = lentilSoupTree.insert(lentilSoupTree.root, "red powdered pepper");

            AVLTree yoghurtSoupTree = new AVLTree();
            yoghurtSoupTree.root = yoghurtSoupTree.insert(yoghurtSoupTree.root, "yoghurt");
            yoghurtSoupTree.root = yoghurtSoupTree.insert(yoghurtSoupTree.root, "butter");
            yoghurtSoupTree.root = yoghurtSoupTree.insert(yoghurtSoupTree.root, "flour");
            yoghurtSoupTree.root = yoghurtSoupTree.insert(yoghurtSoupTree.root, "salt");
            yoghurtSoupTree.root = yoghurtSoupTree.insert(yoghurtSoupTree.root, "lemon juice");
            yoghurtSoupTree.root = yoghurtSoupTree.insert(yoghurtSoupTree.root, "egg yolk");
            yoghurtSoupTree.root = yoghurtSoupTree.insert(yoghurtSoupTree.root, "chili pepper");
            yoghurtSoupTree.root = yoghurtSoupTree.insert(yoghurtSoupTree.root, "water");
            yoghurtSoupTree.root = yoghurtSoupTree.insert(yoghurtSoupTree.root, "rice");
            yoghurtSoupTree.root = yoghurtSoupTree.insert(yoghurtSoupTree.root, "olive oil");
            yoghurtSoupTree.root = yoghurtSoupTree.insert(yoghurtSoupTree.root, "dried mint");

            AVLTree ezogelinSoupTree = new AVLTree();
            ezogelinSoupTree.root = ezogelinSoupTree.insert(ezogelinSoupTree.root, "red lentil");
            ezogelinSoupTree.root = ezogelinSoupTree.insert(ezogelinSoupTree.root, "rice");
            ezogelinSoupTree.root = ezogelinSoupTree.insert(ezogelinSoupTree.root, "bulgur wheat");
            ezogelinSoupTree.root = ezogelinSoupTree.insert(ezogelinSoupTree.root, "onion");
            ezogelinSoupTree.root = ezogelinSoupTree.insert(ezogelinSoupTree.root, "tomato paste");
            ezogelinSoupTree.root = ezogelinSoupTree.insert(ezogelinSoupTree.root, "butter");
            ezogelinSoupTree.root = ezogelinSoupTree.insert(ezogelinSoupTree.root, "hot water");
            ezogelinSoupTree.root = ezogelinSoupTree.insert(ezogelinSoupTree.root, "mint");
            ezogelinSoupTree.root = ezogelinSoupTree.insert(ezogelinSoupTree.root, "salt");

            AVLTree tarhanaSoupTree = new AVLTree();
            tarhanaSoupTree.root = tarhanaSoupTree.insert(tarhanaSoupTree.root, "homemade tarhana");
            tarhanaSoupTree.root = tarhanaSoupTree.insert(tarhanaSoupTree.root, "tomato paste");
            tarhanaSoupTree.root = tarhanaSoupTree.insert(tarhanaSoupTree.root, "mint");
            tarhanaSoupTree.root = tarhanaSoupTree.insert(tarhanaSoupTree.root, "liquid oil");
            tarhanaSoupTree.root = tarhanaSoupTree.insert(tarhanaSoupTree.root, "water");
            tarhanaSoupTree.root = tarhanaSoupTree.insert(tarhanaSoupTree.root, "salt");

            AVLTree broccoliSoupTree = new AVLTree();
            broccoliSoupTree.root = broccoliSoupTree.insert(broccoliSoupTree.root, "broccoli");
            broccoliSoupTree.root = broccoliSoupTree.insert(broccoliSoupTree.root, "water");
            broccoliSoupTree.root = broccoliSoupTree.insert(broccoliSoupTree.root, "olive oil");
            broccoliSoupTree.root = broccoliSoupTree.insert(broccoliSoupTree.root, "butter");
            broccoliSoupTree.root = broccoliSoupTree.insert(broccoliSoupTree.root, "flour");
            broccoliSoupTree.root = broccoliSoupTree.insert(broccoliSoupTree.root, "salt");
            broccoliSoupTree.root = broccoliSoupTree.insert(broccoliSoupTree.root, "black pepper");

            AVLTree sautedMeatTree = new AVLTree();
            sautedMeatTree.root = sautedMeatTree.insert(sautedMeatTree.root, "cubed beef");
            sautedMeatTree.root = sautedMeatTree.insert(sautedMeatTree.root, "capia pepper (coarsely chopped)");
            sautedMeatTree.root = sautedMeatTree.insert(sautedMeatTree.root, "green pepper (coarsely chopped)");
            sautedMeatTree.root = sautedMeatTree.insert(sautedMeatTree.root, "onion (chopped coarsely)");
            sautedMeatTree.root = sautedMeatTree.insert(sautedMeatTree.root, "tomatoes (tomato paste can also be used)");
            sautedMeatTree.root = sautedMeatTree.insert(sautedMeatTree.root, "pepper paste");
            sautedMeatTree.root = sautedMeatTree.insert(sautedMeatTree.root, "liquid oil");
            sautedMeatTree.root = sautedMeatTree.insert(sautedMeatTree.root, "butter");
            sautedMeatTree.root = sautedMeatTree.insert(sautedMeatTree.root, "salt");
            sautedMeatTree.root = sautedMeatTree.insert(sautedMeatTree.root, "black pepper");
            sautedMeatTree.root = sautedMeatTree.insert(sautedMeatTree.root, "chili pepper");
            sautedMeatTree.root = sautedMeatTree.insert(sautedMeatTree.root, "oregano");
            sautedMeatTree.root = sautedMeatTree.insert(sautedMeatTree.root, "cumin");

            AVLTree patlıcanOturtmaTree = new AVLTree();
            patlıcanOturtmaTree.root = patlıcanOturtmaTree.insert(patlıcanOturtmaTree.root, "eggplants (large)");
            patlıcanOturtmaTree.root = patlıcanOturtmaTree.insert(patlıcanOturtmaTree.root, "minced meat");
            patlıcanOturtmaTree.root = patlıcanOturtmaTree.insert(patlıcanOturtmaTree.root, "medium-sized onion");
            patlıcanOturtmaTree.root = patlıcanOturtmaTree.insert(patlıcanOturtmaTree.root, "peppers (red, green)");
            patlıcanOturtmaTree.root = patlıcanOturtmaTree.insert(patlıcanOturtmaTree.root, "tomatoes (grated)");
            patlıcanOturtmaTree.root = patlıcanOturtmaTree.insert(patlıcanOturtmaTree.root, "garlic");
            patlıcanOturtmaTree.root = patlıcanOturtmaTree.insert(patlıcanOturtmaTree.root, "chili pepper");
            patlıcanOturtmaTree.root = patlıcanOturtmaTree.insert(patlıcanOturtmaTree.root, "red powdered pepper");
            patlıcanOturtmaTree.root = patlıcanOturtmaTree.insert(patlıcanOturtmaTree.root, "salt");
            patlıcanOturtmaTree.root = patlıcanOturtmaTree.insert(patlıcanOturtmaTree.root, "black pepper");
            patlıcanOturtmaTree.root = patlıcanOturtmaTree.insert(patlıcanOturtmaTree.root, "cumin");
            patlıcanOturtmaTree.root = patlıcanOturtmaTree.insert(patlıcanOturtmaTree.root, "oil");
            patlıcanOturtmaTree.root = patlıcanOturtmaTree.insert(patlıcanOturtmaTree.root, "tomato paste");
            patlıcanOturtmaTree.root = patlıcanOturtmaTree.insert(patlıcanOturtmaTree.root, "water");

            AVLTree whiteBeanStewWithMeatTree = new AVLTree();
            whiteBeanStewWithMeatTree.root = whiteBeanStewWithMeatTree.insert(whiteBeanStewWithMeatTree.root, "white bean");
            whiteBeanStewWithMeatTree.root = whiteBeanStewWithMeatTree.insert(whiteBeanStewWithMeatTree.root, "onion");
            whiteBeanStewWithMeatTree.root = whiteBeanStewWithMeatTree.insert(whiteBeanStewWithMeatTree.root, "tomato paste");
            whiteBeanStewWithMeatTree.root = whiteBeanStewWithMeatTree.insert(whiteBeanStewWithMeatTree.root, "pepper paste");
            whiteBeanStewWithMeatTree.root = whiteBeanStewWithMeatTree.insert(whiteBeanStewWithMeatTree.root, "cubed veal");
            whiteBeanStewWithMeatTree.root = whiteBeanStewWithMeatTree.insert(whiteBeanStewWithMeatTree.root, "liquid oil");
            whiteBeanStewWithMeatTree.root = whiteBeanStewWithMeatTree.insert(whiteBeanStewWithMeatTree.root, "salt");
            whiteBeanStewWithMeatTree.root = whiteBeanStewWithMeatTree.insert(whiteBeanStewWithMeatTree.root, "chili pepper");
            whiteBeanStewWithMeatTree.root = whiteBeanStewWithMeatTree.insert(whiteBeanStewWithMeatTree.root, "hot water");

            AVLTree mantıTree = new AVLTree();
            mantıTree.root = mantıTree.insert(mantıTree.root, "flour");
            mantıTree.root = mantıTree.insert(mantıTree.root, "warm water");
            mantıTree.root = mantıTree.insert(mantıTree.root, "egg");
            mantıTree.root = mantıTree.insert(mantıTree.root, "salt");
            mantıTree.root = mantıTree.insert(mantıTree.root, "mince");
            mantıTree.root = mantıTree.insert(mantıTree.root, "onion");
            mantıTree.root = mantıTree.insert(mantıTree.root, "black pepper");
            mantıTree.root = mantıTree.insert(mantıTree.root, "chili pepper");
            mantıTree.root = mantıTree.insert(mantıTree.root, "butter");
            mantıTree.root = mantıTree.insert(mantıTree.root, "tomato paste");
            mantıTree.root = mantıTree.insert(mantıTree.root, "garlic");
            mantıTree.root = mantıTree.insert(mantıTree.root, "yoghurt");
            mantıTree.root = mantıTree.insert(mantıTree.root, "mint");
            mantıTree.root = mantıTree.insert(mantıTree.root, "sumac");

            AVLTree karnıyarıkTree = new AVLTree();
            karnıyarıkTree.root = karnıyarıkTree.insert(karnıyarıkTree.root, "aubergine");
            karnıyarıkTree.root = karnıyarıkTree.insert(karnıyarıkTree.root, "olive oil");
            karnıyarıkTree.root = karnıyarıkTree.insert(karnıyarıkTree.root, "onion");
            karnıyarıkTree.root = karnıyarıkTree.insert(karnıyarıkTree.root, "green pepper");
            karnıyarıkTree.root = karnıyarıkTree.insert(karnıyarıkTree.root, "mince");
            karnıyarıkTree.root = karnıyarıkTree.insert(karnıyarıkTree.root, "garlic");
            karnıyarıkTree.root = karnıyarıkTree.insert(karnıyarıkTree.root, "pepper paste");
            karnıyarıkTree.root = karnıyarıkTree.insert(karnıyarıkTree.root, "tomato paste");
            karnıyarıkTree.root = karnıyarıkTree.insert(karnıyarıkTree.root, "salt");
            karnıyarıkTree.root = karnıyarıkTree.insert(karnıyarıkTree.root, "black pepper");
            karnıyarıkTree.root = karnıyarıkTree.insert(karnıyarıkTree.root, "tomatoes");
            karnıyarıkTree.root = karnıyarıkTree.insert(karnıyarıkTree.root, "garlic");
            karnıyarıkTree.root = karnıyarıkTree.insert(karnıyarıkTree.root, "green pepper");
            karnıyarıkTree.root = karnıyarıkTree.insert(karnıyarıkTree.root, "parsley");
            karnıyarıkTree.root = karnıyarıkTree.insert(karnıyarıkTree.root, "sunflower oil");

            AVLTree yaprakSarmaTree = new AVLTree();
            yaprakSarmaTree.root = yaprakSarmaTree.insert(yaprakSarmaTree.root, "vine leaves");
            yaprakSarmaTree.root = yaprakSarmaTree.insert(yaprakSarmaTree.root, "lemon");
            yaprakSarmaTree.root = yaprakSarmaTree.insert(yaprakSarmaTree.root, "olive oil");
            yaprakSarmaTree.root = yaprakSarmaTree.insert(yaprakSarmaTree.root, "hot water");
            yaprakSarmaTree.root = yaprakSarmaTree.insert(yaprakSarmaTree.root, "pinenuts");
            yaprakSarmaTree.root = yaprakSarmaTree.insert(yaprakSarmaTree.root, "currants");
            yaprakSarmaTree.root = yaprakSarmaTree.insert(yaprakSarmaTree.root, "onion");
            yaprakSarmaTree.root = yaprakSarmaTree.insert(yaprakSarmaTree.root, "rice");
            yaprakSarmaTree.root = yaprakSarmaTree.insert(yaprakSarmaTree.root, "salt");
            yaprakSarmaTree.root = yaprakSarmaTree.insert(yaprakSarmaTree.root, "black pepper");
            yaprakSarmaTree.root = yaprakSarmaTree.insert(yaprakSarmaTree.root, "mint");
            yaprakSarmaTree.root = yaprakSarmaTree.insert(yaprakSarmaTree.root, "allspice");
            yaprakSarmaTree.root = yaprakSarmaTree.insert(yaprakSarmaTree.root, "cinnamon");


            AVLTree artichokeWithOliveOilTree = new AVLTree();
            artichokeWithOliveOilTree.root = artichokeWithOliveOilTree.insert(artichokeWithOliveOilTree.root, "olive oil");
            artichokeWithOliveOilTree.root = artichokeWithOliveOilTree.insert(artichokeWithOliveOilTree.root, "onion");
            artichokeWithOliveOilTree.root = artichokeWithOliveOilTree.insert(artichokeWithOliveOilTree.root, "potatoes");
            artichokeWithOliveOilTree.root = artichokeWithOliveOilTree.insert(artichokeWithOliveOilTree.root, "carrot");
            artichokeWithOliveOilTree.root = artichokeWithOliveOilTree.insert(artichokeWithOliveOilTree.root, "peas");
            artichokeWithOliveOilTree.root = artichokeWithOliveOilTree.insert(artichokeWithOliveOilTree.root, "artichokes");
            artichokeWithOliveOilTree.root = artichokeWithOliveOilTree.insert(artichokeWithOliveOilTree.root, "lemon juice");
            artichokeWithOliveOilTree.root = artichokeWithOliveOilTree.insert(artichokeWithOliveOilTree.root, "water");
            artichokeWithOliveOilTree.root = artichokeWithOliveOilTree.insert(artichokeWithOliveOilTree.root, "granulated sugar");
            artichokeWithOliveOilTree.root = artichokeWithOliveOilTree.insert(artichokeWithOliveOilTree.root, "salt");

            AVLTree zucchiniStewTree = new AVLTree();
            zucchiniStewTree.root = zucchiniStewTree.insert(zucchiniStewTree.root, "courgette");
            zucchiniStewTree.root = zucchiniStewTree.insert(zucchiniStewTree.root, "olive oil");
            zucchiniStewTree.root = zucchiniStewTree.insert(zucchiniStewTree.root, "tomato paste");
            zucchiniStewTree.root = zucchiniStewTree.insert(zucchiniStewTree.root, "onion");
            zucchiniStewTree.root = zucchiniStewTree.insert(zucchiniStewTree.root, "tomatoes");
            zucchiniStewTree.root = zucchiniStewTree.insert(zucchiniStewTree.root, "sugar");
            zucchiniStewTree.root = zucchiniStewTree.insert(zucchiniStewTree.root, "black pepper");
            zucchiniStewTree.root = zucchiniStewTree.insert(zucchiniStewTree.root, "salt");
            zucchiniStewTree.root = zucchiniStewTree.insert(zucchiniStewTree.root, "dill");

            AVLTree cauliflowerWithOliveOilTree = new AVLTree();
            cauliflowerWithOliveOilTree.root = cauliflowerWithOliveOilTree.insert(cauliflowerWithOliveOilTree.root, "cauliflower");
            cauliflowerWithOliveOilTree.root = cauliflowerWithOliveOilTree.insert(cauliflowerWithOliveOilTree.root, "onion");
            cauliflowerWithOliveOilTree.root = cauliflowerWithOliveOilTree.insert(cauliflowerWithOliveOilTree.root, "garlic");
            cauliflowerWithOliveOilTree.root = cauliflowerWithOliveOilTree.insert(cauliflowerWithOliveOilTree.root, "olive oil");
            cauliflowerWithOliveOilTree.root = cauliflowerWithOliveOilTree.insert(cauliflowerWithOliveOilTree.root, "carrot");
            cauliflowerWithOliveOilTree.root = cauliflowerWithOliveOilTree.insert(cauliflowerWithOliveOilTree.root, "tomatoes");
            cauliflowerWithOliveOilTree.root = cauliflowerWithOliveOilTree.insert(cauliflowerWithOliveOilTree.root, "ground pepper");
            cauliflowerWithOliveOilTree.root = cauliflowerWithOliveOilTree.insert(cauliflowerWithOliveOilTree.root, "black pepper");
            cauliflowerWithOliveOilTree.root = cauliflowerWithOliveOilTree.insert(cauliflowerWithOliveOilTree.root, "salt");


        // Tarifi yazdır
        System.out.println("Lazanya Malzemeleri");
        lasagnaTree.printIngredients();
        System.out.println("----------");
        System.out.println("Mercimek Çorbası Malzemeleri");
        lentilSoupTree.printIngredients();
        System.out.println("----------");
        System.out.println("Hünkar Beğendi Malzemeleri");
        hunkarBegendiTree.printIngredients();
        System.out.println("----------");
        System.out.println("Karnıyarık Malzemeleri");
        karnıyarıkTree.printIngredients();


        // Karnıyarık tarifi yazdır
        PrintSteps.karnıyarıkPrintSteps();

    }
}
