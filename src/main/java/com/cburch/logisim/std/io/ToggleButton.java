/* Copyright (c) 2010, Carl Burch. License information is located in the
 * com.cburch.logisim.Main source code and at www.cburch.com/logisim/. */

package com.cburch.logisim.std.io;

import java.awt.event.MouseEvent;

import com.cburch.logisim.data.Value;
import com.cburch.logisim.instance.InstanceDataSingleton;

import com.cburch.logisim.instance.InstancePoker;
import com.cburch.logisim.instance.InstanceState;
import static com.cburch.logisim.util.LocaleString.*;

public class ToggleButton extends Button {
	private static final int DEPTH = 3;

	public ToggleButton() {
		super("ToggleButton", "toggleButtonComponent");
		setInstancePoker(Poker.class);
	}

	public static class Poker extends InstancePoker {
	  Value value = Value.TRUE;
	  
		@Override
		public void mousePressed(InstanceState state, MouseEvent e) {
		  if (value == Value.TRUE)
		  {
		    value = Value.FALSE;
		    setValue(state, value);
		  }
		  else if (value == Value.FALSE)
		  {
		    value = Value.TRUE;
		    setValue(state, value);
		  }
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
