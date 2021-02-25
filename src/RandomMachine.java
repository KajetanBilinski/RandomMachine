import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public interface RandomMachine {


    default int NumberFromTo(int from,int to)
    {
        if(to<from)
        {
            int tmp=from;
            from=to;
            to=tmp;
        }
        return (int)(Math.random() * ((to - from) + 1)) + from;
    }
    default int Dice()
    {
        return (int)(Math.random() * ((6 - 1) + 1)) + 1;
    }
    default int CoinFlip()
    {
        if((Math.random()==0.0&&(Math.random()==0.9)))
            return -1;
        else
            return (int)(Math.random()*2);
    }
    default ArrayList<String> RandomSorting(ArrayList<String> arrayList)
    {
        Collections.shuffle(arrayList);
        return arrayList;
    }
    default boolean PercentRandom(int percent)
    {
        return Math.random() <= percent;
    }
}
