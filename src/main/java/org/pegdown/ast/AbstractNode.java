package org.pegdown.ast;

public abstract class AbstractNode implements Node {
	private int startIndex;
	private int endIndex;

	public int getStartIndex() {
		return startIndex;
	}

	public int getEndIndex() {
		return endIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

	public void shiftIndices(int delta) {
		startIndex += delta;
		endIndex += delta;
	}

	public void mapIndices(int[] ixMap) {
		startIndex = ixMap[startIndex];
		endIndex = ixMap[endIndex];
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " [" + startIndex + '-' + endIndex + ']';
	}
}
