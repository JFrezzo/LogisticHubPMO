package view;

import java.util.List;

public interface SendObserver extends Observer {
	List<String> searchSolution();
	void setSolidNode(final int row, final int col);
	int getMaxRow();
	int getMaxCol();
	String getStartNode();
	String getGoalNode();
	String getVId();
}
