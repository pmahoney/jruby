/***** BEGIN LICENSE BLOCK *****
 * Version: EPL 1.0/GPL 2.0/LGPL 2.1
 *
 * The contents of this file are subject to the Common Public
 * License Version 1.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of
 * the License at http://www.eclipse.org/legal/cpl-v10.html
 *
 * Software distributed under the License is distributed on an "AS
 * IS" basis, WITHOUT WARRANTY OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * rights and limitations under the License.
 *
 * Copyright (C) 2002-2004 Anders Bengtsson <ndrsbngtssn@yahoo.se>
 * Copyright (C) 2001-2004 Jan Arne Petersen <jpetersen@uni-bonn.de>
 * Copyright (C) 2002 Benoit Cerrina <b.cerrina@wanadoo.fr>
 * Copyright (C) 2004 Thomas E Enebo <enebo@acm.org>
 * Copyright (C) 2004 Stefan Matthias Aust <sma@3plus4.de>
 * 
 * Alternatively, the contents of this file may be used under the terms of
 * either of the GNU General Public License Version 2 or later (the "GPL"),
 * or the GNU Lesser General Public License Version 2.1 or later (the "LGPL"),
 * in which case the provisions of the GPL or the LGPL are applicable instead
 * of those above. If you wish to allow use of your version of this file only
 * under the terms of either the GPL or the LGPL, and not to allow others to
 * use your version of this file under the terms of the EPL, indicate your
 * decision by deleting the provisions above and replace them with the notice
 * and other provisions required by the GPL or the LGPL. If you do not delete
 * the provisions above, a recipient may use your version of this file under
 * the terms of any one of the EPL, the GPL or the LGPL.
 ***** END LICENSE BLOCK *****/
package org.jruby.runtime;

import org.jruby.Ruby;
import org.jruby.runtime.builtin.IRubyObject;

public class GlobalVariable {
    public static class Copy extends GlobalVariable {
        private GlobalVariable other;

        public Copy(Ruby runtime, String name, GlobalVariable other) {
            super(runtime, name, other.get());
            this.other = other;
        }

        @Override
        public IRubyObject get() {
            return other.get();
        }

        @Override
        public IRubyObject set(IRubyObject value) {
            return other.set(value);
        }
    }

    protected final Ruby runtime;

    protected final String name;
    protected volatile IRubyObject value;

    public static String variableName(String name) {
        return "$" + name;
    }

    public GlobalVariable(Ruby runtime, String name, IRubyObject value) {
        assert name.startsWith("$");

        this.runtime = runtime;
        this.name = name;
        this.value = value;
    }

    public String name() {
        return name;
    }

    public IRubyObject get() {
        return value;
    }

    public IRubyObject set(IRubyObject value) {
        this.value = value;
        return value;
    }
}
