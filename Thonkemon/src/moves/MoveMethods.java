package moves;

import java.util.List;

public interface MoveMethods {

	abstract List<Move> getMoveset();

	abstract List<Move> getMoves(int level);
}
