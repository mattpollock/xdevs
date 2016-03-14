/*
 * Copyright (C) 2014-2015 José Luis Risco Martín <jlrisco@ucm.es> and 
 * Saurabh Mittal <smittal@duniptech.com>.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, see
 * http://www.gnu.org/licenses/
 *
 * Contributors:
 *  - José Luis Risco Martín
 */
package xdevs.core.modeling;

import java.util.ArrayList;
import java.util.Collection;

import xdevs.core.modeling.api.ComponentInterface;

/**
 * @author smittal
 *
 */
public abstract class Component extends Entity implements ComponentInterface {

	// Component attributes
	protected ComponentInterface parent = null;
	protected ArrayList<InPort<?>> inPorts = new ArrayList<>();
	protected ArrayList<OutPort<?>> outPorts = new ArrayList<>();

	public Component(){
		this(Component.class.getSimpleName());
	}
	
	public Component(String name){
		super(name);
	}

        @Override
	public boolean isInputEmpty() {
		for (InPort<?> port : inPorts) {
			if (!port.isEmpty()) {
				return false;
			}
		}
		return true;
	}

        @Override
	public void addInPort(InPort<?> port) {
		inPorts.add(port);
		port.parent = this;
	}

        @Override
	public Collection<InPort<?>> getInPorts() {
		return inPorts;
	}

        @Override
	public void addOutPort(OutPort<?> port) {
		outPorts.add(port);
		port.parent = this;
	}

        @Override
	public Collection<OutPort<?>> getOutPorts() {
		return outPorts;
	}

	@Override
	public ComponentInterface getParent() {
		return parent;
	}

	@Override
	public void setParent(ComponentInterface parent) {
		this.parent = parent;
	}

	public String toString(){
		StringBuilder sb = new StringBuilder(name + " :");
		sb.append(" Inports[ ");
		for(InPort<?> p : inPorts){
			sb.append(p + " ");
		}
		sb.append("]");
		sb.append(" Outports[ ");
		for(OutPort<?> p: outPorts){
			sb.append(p+" ");
		}
		sb.append("]");
		return sb.toString();
	}
	
	@Override
	public String getQualifiedName() {
		if (parent == null) {
			return name;
		}
		return parent.getQualifiedName() + "." + name;
	}

}
