/* Copyright (c) 2010, Carl Burch. License information is located in the
 * com.cburch.logisim.Main source code and at www.cburch.com/logisim/. */

package com.cburch.logisim.std.io;

import java.awt.event.MouseEvent;

import com.cburch.logisim.data.Value;
import com.cburch.logisim.instance.InstanceDataSingleton;

import com.cburch.logisim.instance.InstancePoker;
import com.cburch.logisim.instance.InstanceState;
import static com.cburch.logisim.util.LocaleString.*;

public class HoldButton extends Button {
	private static final int DEPTH = 3;

	public HoldButton() {
		super("Button", "buttonComponent");
		setInstancePoker(Poker.class);
	}
	
  public static class Poker extends InstancePoker {
    @Override
    public void mousePressed(InstanceState state, MouseEvent e) {
        setValue(state, Value.TRUE);
    }

    @Override
    public void mouseReleased(InstanceState state, MouseEvent e) {
        setValue(state, Value.FALSE);
    }

    private void setValue(InstanceState state, Value val) {
        InstanceDataSingleton data = (InstanceDataSingleton) state.getData();
        if (data == null) {
            state.setData(new InstanceDataSingleton(val));
        } else {
            data.setValue(val);
        }
        state.getInstance().fireInvalidated();
    }
  }
}