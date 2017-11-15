/*
 * Yet Another UserAgent Analyzer
 * Copyright (C) 2013-2017 Niels Basjes
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package nl.basjes.parse.useragent.analyze.treewalker.steps.walk;

import nl.basjes.parse.useragent.analyze.treewalker.steps.Step;
import nl.basjes.parse.useragent.analyze.treewalker.steps.WalkList.WalkResult;
import org.antlr.v4.runtime.tree.ParseTree;

public class StepPrev extends Step {

    private ParseTree prev(ParseTree tree) {
        ParseTree parent = up(tree);

        ParseTree prevChild = null;
        ParseTree child = null;
        int i;
        for (i = 0; i < parent.getChildCount(); i++) {
            if (!treeIsSeparator(child)) {
                prevChild = child;
            }
            child = parent.getChild(i);
            if (child == tree) {
                return prevChild;
            }
        }
        return null; // There is no previous
    }

    @Override
    public WalkResult walk(ParseTree tree, String value) {
        ParseTree nextTree = prev(tree);
        if (nextTree == null) {
            return null;
        }

        return walkNextStep(nextTree, null);
    }

    @Override
    public String toString() {
        return "Prev()";
    }

}
