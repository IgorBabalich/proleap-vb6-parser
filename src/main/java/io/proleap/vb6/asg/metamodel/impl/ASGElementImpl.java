/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.impl;

import java.util.Collection;
import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;

import io.proleap.vb6.asg.applicationcontext.VbParserContext;
import io.proleap.vb6.asg.metamodel.ASGElement;
import io.proleap.vb6.asg.registry.ASGElementRegistry;
import io.proleap.vb6.asg.util.ANTLRUtils;

/**
 * http://en.wikipedia.org/wiki/Abstract_semantic_graph
 */
public abstract class ASGElementImpl implements ASGElement {

	protected final ParseTree ctx;

	public ASGElementImpl(final ParseTree ctx) {
		this.ctx = ctx;
	}

	@Override
	public Collection<ASGElement> getChildren() {
		final ASGElementRegistry asgElementRegistry = VbParserContext.getInstance().getASGElementRegistry();
		final List<ASGElement> result = ANTLRUtils.findASGElementChildren(ctx, asgElementRegistry);
		return result;
	}

	@Override
	public ParseTree getCtx() {
		return ctx;
	}

	@Override
	public ASGElement getParent() {
		final ASGElementRegistry asgElementRegistry = VbParserContext.getInstance().getASGElementRegistry();
		final ASGElement result = ANTLRUtils.findParent(ASGElement.class, ctx, asgElementRegistry);
		return result;
	}

}
