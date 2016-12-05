/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.asg.metamodel.call;

import java.util.List;

public interface MembersCall extends Call {

	void addSubCall(Call call);

	Call getLastSubCall();

	List<Call> getSubCalls();
}
