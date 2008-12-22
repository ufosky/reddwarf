 /*
 * Copyright 2007-2008 Sun Microsystems, Inc.
 *
 * This file is part of Project Darkstar Server.
 *
 * Project Darkstar Server is free software: you can redistribute it
 * and/or modify it under the terms of the GNU General Public License
 * version 2 as published by the Free Software Foundation and
 * distributed hereunder to you.
 *
 * Project Darkstar Server is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.sun.sgs.protocol;

import com.sun.sgs.service.Node;
import java.util.Collection;

/**
 * An exception that indicates a login should be redirected to the node
 * returned by the exception's {@link #getNode getNode} method.
 *
 * @see LoginCompletionFuture
 */
public class LoginRedirectException extends Exception {

    /** The serial version for this class. */
    private static final long serialVersionUID = 1L;

    /** The node. */
    private final Node node;

    /** The protocol descriptors for the {@code node}. */
    private final Collection<ProtocolDescriptor> descriptors;

    /**
     * Constructs and instance with the specified {@code node} and
     * protocol {@code descriptors}.
     *
     * @param	node a node
     * @param	descriptors a collection of protocol descriptors
     *		supported by the specified {@code node}, or {@code null}
     */
    public LoginRedirectException(
	Node node, Collection<ProtocolDescriptor> descriptors)
    {
	this(node, descriptors, null);
    }
    
    /**
     * Constructs and instance with the specified {@code node}, {@code
     * descriptors} and detail {@code message}.
     *
     * @param	node a node
     * @param	descriptors a collection of protocol descriptors
     *		supported by the specified {@code node}, or {@code null}
     * @param	message a detail message, or {@code null}
     */
    public LoginRedirectException(
	Node node, Collection<ProtocolDescriptor> descriptors,
	String message)
    {
	this(node, descriptors, message, null);
    }
    
    /**
     * Constructs and instance with the specified {@code node}, detail
     * {@code message}, and {@code cause}.
     *
     * @param	node a node
     * @param	descriptors a collection of protocol descriptors
     *		supported by the specified {@code node}, or {@code null}
     * @param	message a detail message, or {@code null}
     * @param	cause the cause of this exception, or {@code null}
     */
    public LoginRedirectException(
	Node node, Collection<ProtocolDescriptor> descriptors,
	String message, Throwable cause)
    {
	super(message, cause);
	if (node == null) {
	    throw new NullPointerException("null node");
	}
	this.node = node;
	this.descriptors = descriptors;
    }

    /**
     * Returns the node to which the login should be redirected.
     *
     * @return	the node to which the login should be redirected
     */
    public Node getNode() {
	return node;
    }
    
    /**
     * Returns a collection of protocol descriptors supported by
     * the node returned by {@link #getNode getNode}, or {@code
     * null} if the node has no protocol descriptors
     *
     * @return	a collection of protocol descriptors, or {@code null}
     */
    public Collection<ProtocolDescriptor> getProtocolDescriptors() {
	return descriptors;
    }
}
