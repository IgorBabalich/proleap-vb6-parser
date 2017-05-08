/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel;

import java.util.Collection;

import org.antlr.v4.runtime.ParserRuleContext;

/**
 * an element of the ASG that corresponds to an element in the AST.
 */
public interface ASGElement extends ModelElement {

	Collection<ASGElement> getChildren();

	ParserRuleContext getCtx();

	ASGElement getParent();

	Program getProgram();

}
