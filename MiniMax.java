import java.util.*;
import java.lang.Math;

public class PerfectAI extends AIModule
{
	private class State
	{
		private int value;
		private int index;
		private int depth;

		State()
		{
			value = -Integer.MAX_VALUE;
			index = 0;
			depth = 0;
		}

		State(int value, int index, int depth)
		{
			this.value = value;
			this.index = index;
			this.depth = depth;
		}

		public int getValue()
		{
			return value;
		}
		public int getIndex()
		{
			return index;
		}
		public int getDepth()
		{
			return depth;
		}
		public void setValue(int value)
		{
			this.value = value;
		}

	}



	public void getNextMove(final GameStateModule game)
	{
	    	while(!terminate)
       		{
            		int depthLimit = 5;
            		MiniMaxDecision(game, new State(-1, -1, 0), depthLimit);
        	}

	}

	private void MiniMaxDecision(final GameStateModule game, State CurrentState, int depthLimit)
	{
		int ourID = game.getActivePlayer();
		int theirID = 3 - ourID;
	    //v = value of eval fn at one state
	    chosenMove = getMax(game, CurrentState, depthLimit, ourID, theirID, true).getIndex();

	}

	private State getMax(final GameStateModule game, State CurrentState, int depthLimit, int ourID, int theirID, boolean firstMove)
	{
		if(game.isGameOver())
		{
			return eval(game, CurrentState, ourID, theirID);
		}
	    	if(CurrentState.getDepth() == depthLimit)
        	{
            		return eval(game, CurrentState, ourID, theirID);
        	}

        	State MinState = new State(-Integer.MAX_VALUE, -1, 0); //-2147483648

		if(firstMove) {
        		for(int i = 0; i < game.getWidth(); i++)
        		{
            			if(game.canMakeMove(i))
            			{
                			game.makeMove(i);
                			MinState = max(MinState, getMin(game, new State(CurrentState.getValue(), i, CurrentState.getDepth() + 1), depthLimit, ourID, theirID));
                			game.unMakeMove();
                            chosenMove = MinState.getIndex();
            			}
        		}
		}

		else{
        	for(int i = 0; i < game.getWidth(); i++)
        	{
            		if(game.canMakeMove(i))
            		{
                		game.makeMove(i);
                		MinState = max(MinState, getMin(game, new State(CurrentState.getValue(), CurrentState.getIndex(), CurrentState.getDepth() + 1), depthLimit, ourID, theirID));
                		game.unMakeMove();
                		chosenMove = MinState.getIndex();
                    }
        	}}


        	return MinState;
	}

	private State getMin(final GameStateModule game, State CurrentState, int depthLimit, int ourID, int theirID)
	{

		if(game.isGameOver())
		{
			return eval(game, CurrentState, ourID, theirID);
		}
	    	if(CurrentState.getDepth() == depthLimit)
        	{
            		return eval(game, CurrentState, ourID, theirID);
        	}

        	State MaxState = new State(Integer.MAX_VALUE, 50, 0); //2147483648

        	for(int i = 0; i < game.getWidth(); i++)
        	{
            		if(game.canMakeMove(i))
            		{
                        chosenMove = MaxState.getIndex();
                		game.makeMove(i);
                		MaxState = min(MaxState, getMax(game, new State(CurrentState.getValue(), CurrentState.getIndex(), CurrentState.getDepth() + 1), depthLimit, ourID, theirID, false));
                		game.unMakeMove();
                    }
        	}
        	return MaxState;
	}

	private State max(State s1, State s2)
	{
		if(s2.getValue() > s1.getValue())
			return s2;
		return s1;
	}

	private State min(State s1, State s2)
	{
		if(s2.getValue() < s1.getValue())
		{
			return s2;
		}
		return s1;
	}

private State eval(final GameStateModule state, State CurrentState, int usId, int themId)
{

    boolean counted = false;
    int x = 0;
    int y = 0;
    int width = state.getWidth();
    int height = state.getHeight();
    int mineinrow = 0;
    int theminrow = 0;
    int usPlay = 0;
    int themPlay = 0;
    int j = 0;
    int cont = 0;
    int waysToWin = 0;
    int numTwos = 0;
    int numThrees = 0;
    int usDoubleThrees = 0;
    int themThrees = 0;
    int themDoubleThrees = 0;
    int themTwos = 0;
    int usWin = 0;
    int usCanWin = 0;
    int usCouldWin = 0;
    int themWin = 0;
    int themCanWin = 0;
    int themCouldWin = 0;
    int mine = usId;
    int opponent = themId;
    int value = 0;
    int constMine = 0;
    int constUs = 0;
    int constThem = 0;
    int constThemPlay = 0;
    int constBob = 0;
    int bob = 0;
    int avoid = 0;
    int play = 0;
    int[][] winningSpot = new int[width][height];

    for (x = 0; x < width; x++)
    {
 
        for (y = 0; y < height; y++)
        {
            int xcoor = -1;
            int ycoor = -1;
            double widthFact = 1;
            usWin = 0; themWin = 0; themThrees = 0; themTwos = 0; numThrees = 0; usDoubleThrees = 0; numTwos = 0; waysToWin = 0; themCanWin = 0; themDoubleThrees = 0; avoid = 0; usCanWin = 0;
            mineinrow = 0; theminrow = 0; usPlay = 0; themPlay = 0; bob = 0;
            if (state.getAt(x,y) == mine){
                if(x == 1 || x == 5)
                    widthFact = 1.25;
                else if (x == 2 || x == 4)
                    widthFact = 1.5;
                else if (x == 3)
                    widthFact = 1.75;
                ++mineinrow; ++usPlay; constMine = 1; constUs = 1; constThem = 0; constThemPlay = 0; constBob = 0;}
            else if (state.getAt(x,y) == opponent){
                if(x == 1 || x == 5)
                    widthFact = 1.25;
                else if (x == 2 || x == 4)
                    widthFact = 1.5;
                else if (x == 3)
                    widthFact = 1.75;
                ++theminrow; ++themPlay; constThem = 1; constThemPlay = 1; constMine = 0; constUs = 0; constBob = 0;}
            else
            {
                if (state.getAt(x, y-1) == 0  && y > 0)
                    break;
                else{
                    ++usPlay; ++themPlay; constUs = 1; constThemPlay = 1; constMine = 0; constThem = 0; constBob = 1;
                    if(state.getAt(x-1,y) == 0 && state.getAt(x-2,y) == 0 && state.getAt(x-3,y) == 0 && x > 2 && state.getAt(x-3,y-1) == 0 && y > 0)
                        ++waysToWin;}
            }

            for (j = 0; (j <= 2) && (j+x+1 < width); ++j)
            {
                if (state.getAt(x+j,y) != state.getAt(x+j+1,y) && (state.getAt(x+j,y) != 0 && state.getAt(x+j+1,y) != 0)){
                    break; }
                if (state.getAt(x+j+1,y) == mine){
                    ++mineinrow; ++usPlay;}
                else if (state.getAt(x+j+1,y) == opponent){
                    ++theminrow; ++themPlay;}
                else{
                    ++usPlay; ++themPlay; xcoor = x+j+1; ycoor = y;
                    if(y == 0 || state.getAt(x+j+1,y-1) != 0)
                        bob = 1;
                    }
            }
            if (mineinrow >= 2 && state.getAt(x+1,y) == mine && state.getAt(x,y) == mine && usPlay >= 4){
                ++numTwos;}
            if (mineinrow >= 3 && usPlay >= 4){                                    //for horizontal
                if (state.getAt(x+1,y) != 0 && state.getAt(x+2,y) != 0)
                    ++usDoubleThrees;
                else
                    ++numThrees;
                if (bob == 1){
                    ++usCanWin;
                }
 
            }
            if (theminrow >= 2 && state.getAt(x+1,y) == opponent && themPlay >= 4)
                ++themTwos;
            if (theminrow >= 3 && themPlay >= 4)
            {

                if (state.getAt(x+1,y) != 0 && state.getAt(x+2,y) != 0)
                    ++themDoubleThrees;
                else
                    ++themThrees;

                if (bob == 1){
                    ++themCanWin;
                }
            }
            if (theminrow == 4){
                ++themWin;}
            if (mineinrow == 4){
                ++usWin;}
            if (usPlay == 4){
                ++waysToWin;}
            mineinrow = constMine; theminrow = constThem; usPlay = constUs; themPlay = constThemPlay; bob = constBob;


            for (j = 0; (j <= 2) && (j+y+1 < height); ++j)
            {
                if (state.getAt(x,y+j) != state.getAt(x,y+j+1) && (state.getAt(x,y+j) != 0 && state.getAt(x,y+j+1) != 0)){
                   break; }
                if (state.getAt(x,y+j+1) == mine){
                    ++mineinrow; ++usPlay;}
                else if (state.getAt(x,y+j+1) == opponent){
                    ++theminrow; ++themPlay;}
                else{
                    ++usPlay; ++themPlay; xcoor = x; ycoor = y+j+1;
                    if(state.getAt(x,y+j) != 0)
                        bob = 1;
                    }
            }
            if (mineinrow >= 2 && state.getAt(x,y+1) == mine && state.getAt(x,y) == mine && usPlay >= 4)
                ++numTwos;
            if (mineinrow >= 3 && usPlay >= 4){
                if (state.getAt(x,y+1) != 0 && state.getAt(x,y+2) != 0)
                    ++usDoubleThrees;
                else
                    ++numThrees;
                if (bob == 1){
                    ++usCanWin;
                }
            }           			                                            //for vertical
            if (theminrow >= 2 && state.getAt(x,y+1) == opponent && themPlay >= 4)
                ++themTwos;
            if (theminrow >= 3 && themPlay >= 4)
            {

                if (state.getAt(x,y+1) != 0 && state.getAt(x,y+2) != 0)
                    ++themDoubleThrees;
                else
                    ++themThrees;
                if (bob == 1){
                    ++themCanWin;
                }
            }
            if (theminrow == 4){
                ++themWin;}
            if (mineinrow == 4){
                ++usWin;}
            if (usPlay == 4)
                ++waysToWin;
            mineinrow = constMine; theminrow = constThem; usPlay = constUs; themPlay = constThemPlay; bob = constBob;


            for (j = 0; (j <= 2) && (j+y+1 < height) && (j+x+1 < width); ++j)
            {
                if (state.getAt(x+j,y+j) != state.getAt(x+j+1,y+j+1) && (state.getAt(x+j,y+j) != 0 && state.getAt(x+j+1,y+j+1) != 0))
                    break;
                if (state.getAt(x+j+1,y+j+1) == mine){
                    ++mineinrow; ++usPlay;}
                else if (state.getAt(x+j+1,y+j+1) == opponent){
                    ++theminrow; ++themPlay;}
                else{
                    ++usPlay; ++themPlay; xcoor = x+j+1; ycoor = y+j+1;
                    if(state.getAt(x+j+1,y+j) != 0){
                        bob = 1;}

                    }
            }
            if (mineinrow >= 2 && state.getAt(x+1,y+1) == mine && state.getAt(x,y) == mine && usPlay >= 4)
                ++numTwos;
            if (mineinrow >= 3 && usPlay >= 4){                                    //for right Diag

                if (state.getAt(x+1,y+1) != 0 && state.getAt(x+2,y+2) != 0)
                    ++usDoubleThrees;
                else
                    ++numThrees;
                if (bob == 1){
                    ++usCanWin;
                }
            }
            if (theminrow >= 2 && state.getAt(x+1,y+1) == opponent && themPlay >= 4)
                ++themTwos;
            if (theminrow >= 3 && themPlay >= 4){

                if (state.getAt(x+1,y+1) != 0 && state.getAt(x+2,y+2) != 0)
                    ++themDoubleThrees;
                else
                    ++themThrees;
                if (bob == 1){
                    ++themCanWin;
                }
            }
            if (theminrow == 4){
                ++themWin;}
            if (mineinrow == 4){
                ++usWin;}
            if (usPlay == 4)
                ++waysToWin;
            mineinrow = constMine; theminrow = constThem; usPlay = constUs; themPlay = constThemPlay; bob = constBob;



            for (j = 0; (j <= 2) && (j+y+1 < height) && (x-j-1 >= 0); ++j)
            {
                if(x-j-1 < 0)
                    break;
                if (state.getAt(x-j,y+j) != state.getAt(x-j-1,y+j+1) && (state.getAt(x-j,y+j) != 0 && state.getAt(x-j-1,y+j+1) != 0))
                    break;
                if (state.getAt(x-j-1,y+j+1) == mine){
                    ++mineinrow; ++usPlay;}
                else if (state.getAt(x-j-1,y+j+1) == opponent){
                    ++theminrow; ++themPlay;}
                else{
                    ++usPlay; ++themPlay; xcoor = x-j-1; ycoor = y+j+1;
                    if(state.getAt(x-j-1,y+j) != 0)
                        bob = 1;
                    }
            }
            if (mineinrow >= 2 && state.getAt(x-1,y+1) == mine && state.getAt(x,y) == mine && usPlay >= 4)
                ++numTwos;
            if (mineinrow >= 3 && usPlay >= 4){                                    //for left Diag

                if (state.getAt(x-1,y+1) != 0 && state.getAt(x-2,y+2) != 0)
                    ++usDoubleThrees;
                else
                    ++numThrees;
                if (bob == 1){
                    ++usCanWin;
                }
            }
            if (theminrow >= 2 && state.getAt(x-1,y+1) == opponent && themPlay >= 4)
                ++themTwos;
            if (theminrow >= 3 && themPlay >= 4){
                if (state.getAt(x-1,y+1) != 0 && state.getAt(x-2,y+2) != 0)
                    ++themDoubleThrees;
                else
                    ++themThrees;
                if (bob == 1){
                    ++themCanWin;
                }
            }
            if (theminrow == 4){
                ++themWin;}
            if (mineinrow == 4){
                ++usWin;}
            if (usPlay == 4)
                ++waysToWin;
            mineinrow = 0; theminrow = 0; usPlay = 0; themPlay = 0;

            if (usCanWin > 0 && y != 0 && usCouldWin > 0){
                ++play;}
            if (themCanWin > 0 && y != 0 && themCouldWin > 0){
                ++avoid;}

            value += 1000*usWin - 1000*themWin - 500*themCanWin - 750*avoid + 750*play + (25*numThrees + 50*usDoubleThrees - 25*themThrees - 50*themDoubleThrees + 10*numTwos - 10*themTwos + waysToWin) * widthFact;

            themCouldWin = themCanWin;
            usCouldWin = usCanWin;


        }

    }

    CurrentState.setValue(value);
    return CurrentState;
}
}
