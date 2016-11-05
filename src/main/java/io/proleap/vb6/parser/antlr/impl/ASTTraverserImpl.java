/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.parser.antlr.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;

import io.proleap.vb6.parser.antlr.ASTTraverser;
import io.proleap.vb6.parser.metamodel.ASGElement;
import io.proleap.vb6.parser.metamodel.oop.Scope;
import io.proleap.vb6.parser.registry.ASGElementRegistry;

public class ASTTraverserImpl implements ASTTraverser {

	@Override
	public <T extends ASGElement> Collection<T> findAncestors(final Class<? extends ASGElement> type,
			final ParseTree from, final ASGElementRegistry asgElementRegistry) {
		ParseTree currentCtx = from;

		final Collection<T> result = new ArrayList<T>();

		while (currentCtx != null) {
			final T parent = findParent(type, currentCtx, asgElementRegistry);

			if (parent != null) {
				currentCtx = parent.getCtx();
				result.add(parent);
			} else {
				currentCtx = null;
			}
		}

		return result;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T extends ASGElement> List<T> findChildren(final Class<? extends ASGElement> type, final ParseTree ctx,
			final ASGElementRegistry asgElementRegistry) {
		final List<ParseTree> children = findChildren(ctx);
		final List<T> result = new ArrayList<T>();

		for (final ParseTree currentChild : children) {
			final ASGElement asgElement = asgElementRegistry.getASGElement(currentChild);

			if (asgElement != null && type.isAssignableFrom(asgElement.getClass())) {
				result.add((T) asgElement);
			}
		}

		return result;
	}

	@Override
	public List<ParseTree> findChildren(final ParseTree ctx) {
		final List<ParseTree> result = new ArrayList<ParseTree>();
		final int n = ctx.getChildCount();

		for (int i = 0; i < n; i++) {
			final ParseTree currentChild = ctx.getChild(i);
			result.add(currentChild);
		}

		return result;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T extends ASGElement> T findParent(final Class<? extends ASGElement> type, final ParseTree from,
			final ASGElementRegistry asgElementRegistry) {
		T result = null;

		ParseTree currentCtx = from;

		while (result == null && currentCtx != null) {
			currentCtx = currentCtx.getParent();

			final ASGElement asgElement = asgElementRegistry.getASGElement(currentCtx);

			if (asgElement != null && type.isAssignableFrom(asgElement.getClass())) {
				result = (T) asgElement;
			}
		}

		return result;
	}

	@Override
	public Scope findParentScope(final ParseTree from, final ASGElementRegistry asgElementRegistry) {
		return findParent(Scope.class, from, asgElementRegistry);
	}

	@Override
	public ASGElement findParentASGElement(final ParseTree from,
			final ASGElementRegistry asgElementRegistry) {
		return findParent(ASGElement.class, from, asgElementRegistry);
	}

	@Override
	public List<ASGElement> findASGElementChildren(final ParseTree from,
			final ASGElementRegistry asgElementRegistry) {
		return findChildren(ASGElement.class, from, asgElementRegistry);
	}
}
