package model;

import java.util.List;

public interface StreetMap {

	List<Node> searchSolution() throws Exception;
	void setSolidNode(final int row, final int col);
	int getmaxCol();
	int getmaxRow();
	Node getStartNode();
	Node getGoalNode();
}
