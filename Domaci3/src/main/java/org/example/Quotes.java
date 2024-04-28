package org.example;

import java.util.HashMap;

public class Quotes {

    private static volatile Quotes instance;
    private static Object mutex = new Object();

    private HashMap<String ,String > quotes = new HashMap<>();
    private Quotes() {
        addEm();
    }

    public static Quotes getInstance() {
        Quotes result = instance;
        if (result == null) {
            synchronized (mutex) {
                result = instance;
                if (result == null)
                    instance = result = new Quotes();
            }
        }
        return result;
    }

    public HashMap<String, String> getQuotes() {
        return quotes;
    }

    private void addEm(){
        quotes.put("Da vidimo sta ima ispod haube","Branko Perisic");
        quotes.put("Ja sam majstor a vi ste moji segrti","Branko Perisic");
        quotes.put("He will win who knows when to fight and when not to fight.","Sun Tzu");
        quotes.put("Sweat more during peace: bleed less during war.","Sun Tzu");
        quotes.put("In the midst of chaos, there is also opportunity.","Sun Tzu");
        quotes.put("Victorious warriors win first and then go to war, while defeated warriors go to war first and then seek to win.","Sun Tzu");
        quotes.put("Even the finest sword plunged into salt water will eventually rust.","Sun Tzu");
        quotes.put("Quickness is the essence of the war.","Sun Tzu");
        quotes.put("Wheels of justice grind slow but grind fine.","Sun Tzu");
        quotes.put("Victory comes from finding opportunities in problems.","Sun Tzu");
        quotes.put("Never venture, never win!","Sun Tzu");
        quotes.put("If the mind is willing, the flesh could go on and on without many things.","Sun Tzu");
        quotes.put("The supreme art of war is to subdue the enemy without fighting.","Sun Tzu");
        quotes.put("Appear weak when you are strong, and strong when you are weak.","Sun Tzu");
        quotes.put("The art of war is of vital importance to the State. It is a matter of life and death, a road either to safety or to ruin. Hence it is a subject of inquiry which can on no account be neglected.","Sun Tzu");
        quotes.put("Build your opponent a golden bridge to retreat across.","Sun Tzu");
        quotes.put("There is no instance of a nation benefiting from prolonged warfare.","Sun Tzu");
        quotes.put("There are not more than five musical notes, yet the combinations of these five give rise to more melodies than can ever be heard. There are not more than five primary colours, yet in combination they produce more hues than can ever been seen. There are not more than five cardinal tastes, yet combinations of them yield more flavours than can ever be tasted.","Sun Tzu");
        quotes.put("Who wishes to fight must first count the cost.","Sun Tzu");
        quotes.put("You have to believe in yourself.","Sun Tzu");
        quotes.put("One may know how to conquer without being able to do it.","Sun Tzu");
        quotes.put("What the ancients called a clever fighter is one who not only wins, but excels in winning with ease.","Sun Tzu");
        quotes.put("One mark of a great soldier is that he fight on his own terms or fights not at all.","Sun Tzu");
        quotes.put("Anger may in time change to gladness; vexation may be succeeded by content. But a kingdom that has once been destroyed can never come again into being; nor can the dead ever be brought back to life.","Sun Tzu");
        quotes.put("There are roads which must not be followed, armies which must not be attacked, towns which must not be besieged, positions which must not be contested, commands of the sovereign which must not be obeyed.","Sun Tzu");
        quotes.put("The general who wins the battle makes many calculations in his temple before the battle is fought. The general who loses makes but few calculations beforehand.","Sun Tzu");
        quotes.put("Strategy without tactics is the slowest route to victory. Tactics without strategy is the noise before defeat.", "Sun Tzu");
        quotes.put("He who is prudent and lies in wait for an enemy who is not, will be victorious.","Sun Tzu");
        quotes.put("A wise general makes a point of foraging of the enemy.","Sun Tzu");
        quotes.put("Move swift as the Wind and closely-formed as the Wood. Attack like the Fire and be still as the Mountain.","Sun Tzu");
        quotes.put("Do not press an enemy at bay.","Sun Tzu");
        quotes.put("For them to perceive the advantage of defeating the enemy, they must also have their rewards.","Sun Tzu");
        quotes.put("If quick, I survive. If not quick, I am lost. This is death.","Sun Tzu");
        quotes.put("To secure ourselves against defeat lies in our own hands, but the opportunity of defeating the enemy is provided by the enemy himself.","Sun Tzu");
        quotes.put("Bravery without forethought, causes a man to fight blindly and desperately like a mad bull. Such an opponent, must not be encountered with brute force, but may be lured into an ambush and slain.","Sun Tzu");
        quotes.put("Ponder and deliberate before you make a move.","Sun Tzu");
        quotes.put("Rewards for good service should not be deferred a single day.","Sun Tzu");
        quotes.put("Begin by seizing something which your opponent holds dear; then he will be amenable to your will.","Sun Tzu");
        quotes.put("If words of command are not clear and distinct, if orders are not thoroughly understood, then the general is to blame. But, if orders are clear and the soldiers nevertheless disobey, then it is the fault of their officers.","Sun Tzu");
        quotes.put("If his forces are united, separate them.","Sun Tzu");
        quotes.put("The skillful tactician may be likened to the shuai-jan. Now the shuai-jan is a snake that is found in the Ch'ang mountains. Strike at its head, and you will be attacked by its tail; strike at its tail, and you will be attacked by its head; strike at its middle, and you will be attacked by head and tail both.","Sun Tzu");
        quotes.put("It is easy to love your friend, but sometimes the hardest lesson to learn is to love your enemy.","Sun Tzu");
        quotes.put("Be where your enemy is not.","Sun Tzu");
        quotes.put("Who does not know the evils of war cannot appreciate its benefits.","Sun Tzu");
        quotes.put("Plan for what it is difficult while it is easy, do what is great while it is small.","Sun Tzu");
        quotes.put("Concentrate your energy and hoard your strength.","Sun Tzu");
        quotes.put("Foreknowledge cannot be gotten from ghosts and spirits, cannot be had by analogy, cannot be found out by calculation. It must be obtained from people, people who know the conditions of the enemy.","Sun Tzu");
        quotes.put("If you fight with all your might, there is a chance of life; where as death is certain if you cling to your corner.","Sun Tzu");
        quotes.put("Do not swallow bait offered by the enemy. Do not interfere with an army that is returning home.","Sun Tzu");
        quotes.put("We cannot enter into alliances until we are acquainted with the designs of our neighbors.","Sun Tzu");
        quotes.put("When the outlook is bright, bring it before their eyes; but tell them nothing when the situation is gloomy.","Sun Tzu");
        quotes.put("The whole secret lies in confusing the enemy, so that he cannot fathom our real intent.","Sun Tzu");
        quotes.put("Hence that general is skillful in attack whose opponent does not know what to defend; and he is skillful in defense whose opponent does not know what to attack.","Sun Tzu");
        quotes.put("Those skilled at making the enemy move do so by creating a situation to which he must conform; they entice him with something he is certain to take, and with lures of ostensible profit they await him in strength.","Sun Tzu");
        quotes.put("Energy may be likened to the bending of a crossbow; decision, to the releasing of a trigger.","Sun Tzu");
        quotes.put("When your army has crossed the border, you should burn your boats and bridges, in order to make it clear to everybody that you have no hankering after home.","Sun Tzu");
        quotes.put("Move not unless you see an advantage; use not your troops unless there is something to be gained; fight not unless the position is critical.","Sun Tzu");
        quotes.put("The general who advances without coveting fame and retreats without fearing disgrace, whose only thought is to protect his country and do good service for his sovereign, is the jewel of the kingdom.","Sun Tzu");
        quotes.put("It is only the enlightened ruler and the wise general who will use the highest intelligence of the army for the purposes of spying, and thereby they achieve great results.","Sun Tzu");
        quotes.put("Let your plans be dark and impenetrable as night, and when you move, fall like a thunderbolt.","Sun Tzu");
        quotes.put("Great results can be achieved with small forces.","Sun Tzu");
        quotes.put("Opportunities multiply as they are seized.","Sun Tzu");
        quotes.put("If soldiers are punished before they have grown attached to you, they will not prove submissive; and, unless submissive, then will be practically useless. If, when the soldiers have become attached to you, punishments are not enforced, they will still be unless.","Sun Tzu");
        quotes.put("Convince your enemy that he will gain very little by attacking you; this will diminish his enthusiasm.","Sun Tzu");
        quotes.put("To know your enemy, you must become your enemy.","Sun Tzu");
        quotes.put("Treat your men as you would your own beloved sons. And they will follow you into the deepest valley.","Sun Tzu");
        quotes.put("When the enemy is relaxed, make them toil. When full, starve them. When settled, make them move.","Sun Tzu");
        quotes.put("So in war, the way is to avoid what is strong, and strike at what is weak.","Sun Tzu");
        quotes.put("Be extremely subtle even to the point of formlessness. Be extremely mysterious even to the point of soundlessness. Thereby you can be the director of the opponent's fate.","Sun Tzu");
        quotes.put("Water shapes its course according to the nature of the ground over which it flows; the soldier works out his victory in relation to the foe whom he is facing.","Sun Tzu");
        quotes.put("When one treats people with benevolence, justice, and righteousness, and reposes confidence in them, the army will be united in mind and all will be happy to serve their leaders.","Sun Tzu");
        quotes.put("Attack is the secret of defense; defense is the planning of an attack.","Sun Tzu");
        quotes.put("Thus the expert in battle moves the enemy, and is not moved by him.","Sun Tzu");
        quotes.put("The wise warrior avoids the battle.","Sun Tzu");
        quotes.put("To win one hundred victories in one hundred battles is not the acme of skill. To subdue the enemy without fighting is the acme of skill.","Sun Tzu");
        quotes.put("To fight and conquer in all our battles is not supreme excellence; supreme excellence consists in breaking the enemy's resistance without fighting.","Sun Tzu");
        quotes.put("In battle, there are not more than two methods of attack–the direct and the indirect; yet these two in combination give rise to an endless series of maneuvers.","Sun Tzu");
        quotes.put("If you know the enemy and know yourself, you need not fear the result of a hundred battles. If you know yourself but not the enemy, for every victory gained you will also suffer a defeat. If you know neither the enemy nor yourself, you will succumb in every battle.","Sun Tzu");
        quotes.put("The greatest victory is that which requires no battle.","Sun Tzu");
        quotes.put("The worst calamities that befall an army arise from hesitation.","Sun Tzu");
        quotes.put("If there is disturbance in the camp, the general's authority is weak.","Sun Tzu");
        quotes.put("All warfare is based on deception. Hence, when we are able to attack, we must seem unable; when using our forces, we must appear inactive; when we are near, we must make the enemy believe we are far away; when far away, we must make him believe we are near.","Sun Tzu");
        quotes.put("Can you imagine what I would do if I could do all I can?","Sun Tzu");
        quotes.put("The peak efficiency of knowledge and strategy is to make conflict unnecessary.","Sun Tzu");
        quotes.put("All men can see these tactics whereby I conquer, but what none can see is the strategy out of which victory is evolved.","Sun Tzu");
        quotes.put("When envoys are sent with compliments in their mouths, it is a sign that the enemy wishes for a truce.","Sun Tzu");
        quotes.put("If your opponent is of choleric temper, seek to irritate him. Pretend to be weak, that he may grow arrogant.","Sun Tzu");
        quotes.put("If ignorant both of your enemy and yourself, you are certain to be in peril.","Sun Tzu");
        quotes.put("The art of giving orders is not to try to rectify the minor blunders and not to be swayed by petty doubts.","Sun Tzu");
        quotes.put("The line between disorder and order lies in logistics.","Sun Tzu");
        quotes.put("Leadership is a matter of intelligence, trustworthiness, humaneness, courage, and sternness.","Sun Tzu");
        quotes.put("If the enemy know not where he will be attacked, he must prepare in every quarter, and so be everywhere weak.","Sun Tzu");
    }

}
