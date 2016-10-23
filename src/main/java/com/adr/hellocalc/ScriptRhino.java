//    HelloCalc (Calculator) is a JavaFX calculator
//    Copyright (C) 2016 Adrián Romero Corchado.
//
//    This file is part of HelloCalc
//
//    This program is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//    This program is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with this program.  If not, see <http://www.gnu.org/licenses/>.
package com.adr.hellocalc;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.concurrent.locks.ReentrantLock;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.Undefined;

/**
 *
 * @author adrian
 */
public class ScriptRhino implements Script {

    private final ReentrantLock lock = new ReentrantLock();
    private Scriptable scope;

    @Override
    public void init() {
        lock.lock();
        try {
            Context cx = Context.enter();
            cx.setOptimizationLevel(-1); // allways interpretive mode
            scope = cx.initStandardObjects();
        } finally {
            Context.exit();
            lock.unlock();
        }
    }

    @Override
    public Object exec(String script) {
        lock.lock();
        try {
            Context cx = Context.enter();
            cx.setOptimizationLevel(-1); // always interpretive mode
            Object result = cx.evaluateString(scope, script, "<cmd>", 1, null);
            if (result instanceof Undefined) {
                return null;
            } else {
                return result;
            }
        } catch (Exception e) {
            return e;
        } finally {
            Context.exit();
            lock.unlock();
        }
    }

    public void init(InputStream in) throws IOException {
        lock.lock();
        try (ObjectInputStream objin = new ObjectInputStream(in)) {
            scope = (Scriptable) objin.readObject();
        } catch (ClassNotFoundException ex) {
            throw new IOException(ex);
        } finally {
            lock.unlock();
        }
    }

    public void save(OutputStream out) throws IOException {
        lock.lock();
        try (ObjectOutputStream objout = new ObjectOutputStream(out)) {
            objout.writeObject(scope);
        } finally {
            lock.unlock();
        }
    }

    public void putScopeObject(String name, Object obj) {
        lock.lock();
        try {
            Context cx = Context.enter();
            cx.setOptimizationLevel(-1); // always interpretive mode
            ScriptableObject.putProperty(scope, name, Context.javaToJS(obj, scope));
        } finally {
            Context.exit();
            lock.unlock();
        }
    }
}
