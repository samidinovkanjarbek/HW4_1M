import java.util.Random;
public class Main {




            public static int bossHealth = 700;
            public static int bossDamage = 50;
            public static String bossDefence;
            public static int[] heroesHealth = {280, 270, 250,230};
            public static int[] heroesDamage = {10, 20, 15,0};
            public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic","Medic"};
            public static int roundNumber;

            public static void main(String[] args) {
                showStatistics();
                while (!isGameOver()) {
                    playRound();
                }
            }

            public static void playRound() {
                roundNumber++;
                chooseBossDefence();
                bossAttacks();
                heroesAttack();
                showStatistics();
                medic();
            }
            public static void medic() {
                Random random = new Random();
                int randomI = random.nextInt(6,40);
                for (int i = 0; i < heroesAttackType.length; i++) {
                    if (heroesAttackType[i].equals("Medic")){
                        continue;
                    }
                    if (heroesHealth[i] <100 && heroesHealth[i]>0){
                        heroesHealth[i] += randomI;
                    }
                    break;
                }

            }
            public static void chooseBossDefence() {
                Random random = new Random();
                int randomIndex = random.nextInt(heroesHealth.length); // 0,1,2
                bossDefence = heroesAttackType[randomIndex];
            }

            public static void heroesAttack() {
                for (int i = 0; i < heroesDamage.length; i++) {
                    if (heroesHealth[i] > 0 && bossHealth > 0) {
                        int damage = heroesDamage[i];
                        if(heroesAttackType[i] == bossDefence){
                            Random random = new Random();
                            int coeff = random.nextInt(9) + 2; //2,3,4,5,6,7,8,9,10
                            damage = heroesDamage[i] * coeff;
                            System.out.println("Critical damage: " + damage);
                        }
                        if (bossHealth - damage < 0) {
                            bossHealth = 0;
                        } else {
                            bossHealth = bossHealth - damage;
                        }
                    }
                }
            }

            public static void bossAttacks() {
                for (int i = 0; i < heroesHealth.length; i++) {
                    if (heroesHealth[i] > 0) {
                        if (heroesHealth[i] - bossDamage < 0) {
                            heroesHealth[i] = 0;
                        } else {
                            heroesHealth[i] = heroesHealth[i] - bossDamage;
                        }
                    }
                }
            }

            public static boolean isGameOver() {
                if (bossHealth <= 0) {
                    System.out.println("Heroes won!!!");
                    return true;
                }
       /* if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;*/
                boolean allHeroesDead = true;
                for (int i = 0; i < heroesHealth.length; i++) {
                    if (heroesHealth[i] > 0) {
                        allHeroesDead = false;
                        break;
                    }
                }
                if (allHeroesDead) {
                    System.out.println("Boss won!!!");
                }
                return allHeroesDead;
            }

            public static void showStatistics() {
        /*String defence;
        if (bossDefence == null) {
            defence = "No defence";
        } else {
            defence = bossDefence;
        }*/
                System.out.println("ROUND " + roundNumber + " ---------------");
                System.out.println("Boss health: " + bossHealth + " damage: " + bossDamage + " defence: " +
                        (bossDefence == null ? "No defence" : bossDefence));
                for (int i = 0; i < heroesHealth.length; i++) {
                    System.out.println(heroesAttackType[i] + " health: " + heroesHealth[i] + " damage: " + heroesDamage[i]);
                }
            }
        }