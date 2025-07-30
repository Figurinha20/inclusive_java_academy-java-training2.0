package io.altar.jseproject.textinterface.states;

import io.altar.jseproject.utils.scannerUtils.ScannerUtils;

public abstract class State {
	public static final ScannerUtils sc = new ScannerUtils();
	
	public abstract int on();
}