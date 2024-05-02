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
class PrintSteps {

    // Karnıyarık tarifini yazdıran metot
    public static void karnıyarıkPrintSteps() {
        Queue<String> karnıyarıkRecipeSteps = new LinkedList<>();

        // Karnıyarık tarifini kuyruğa ekliyoruz
        karnıyarıkRecipeSteps.offer("Bol suda yıkadığınız patlıcanları, pijamalı şekilde soyun ve tuzlu suda bekletin.");
        karnıyarıkRecipeSteps.offer("3 yemek kaşığı zeytinyağını bir tavada kızdırın. Yemeklik doğradığınız 1 adet soğanı da üzerine ekleyip pembeleşene kadar kavurun. 2 adet doğranmış yeşil biberi ilave edip kavurmaya devam edin.");
        karnıyarıkRecipeSteps.offer("350 gram orta yağlı kıymayı da ekleyin ve kavrulmakta olan soğanlarla birlikte renk alıp, suyunu çekene kadar pişirin.");
        karnıyarıkRecipeSteps.offer("Aralarda karıştırıp pişirme işlemine devam ederek sırasıyla; 2 diş sarımsak, yarım tatlı kaşığı domates salçası, yarım tatlı kaşığı biber salçası, 1'er çay kaşığı tuz ve karabiber ekleyin.");
        karnıyarıkRecipeSteps.offer("2 adet küp doğranmış domatesi ekleyin, 5 dakika kadar pişirdikten sonra ocağın altını kısın ve bir avuç doğranmış maydanozu ekleyip son kez karıştırarak ocaktan alın.");
        karnıyarıkRecipeSteps.offer("Acısını alıp, pijamalı soyduğunuz 6 adet patlıcanı kızartın.");
        karnıyarıkRecipeSteps.offer("Fırına dayanıklı bir kaba yerleştirin.");
        karnıyarıkRecipeSteps.offer("Orta kısımlarından patlıcanları yarın.");
        karnıyarıkRecipeSteps.offer("Hazırladığınız iç harçtan bol bol ekleyin.");
        karnıyarıkRecipeSteps.offer("Patlıcanların üzerine birer cherry domates ve biber dilimleri ekleyin.");
        karnıyarıkRecipeSteps.offer("Karnıyarıkları önceden ısıtılmış 170 derece fırında 20-25 dakika kadar pişirdikten sonra, sıcak olarak, dilerseniz tereyağlı pirinç pilavı eşliğinde servis edin, afiyet olsun!");

        // Aşamaları yazdırma döngüsü
        int stepNumber = 1;
        while (!karnıyarıkRecipeSteps.isEmpty()) {
            System.out.println("Adım " + stepNumber + ": " + karnıyarıkRecipeSteps.poll());
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
