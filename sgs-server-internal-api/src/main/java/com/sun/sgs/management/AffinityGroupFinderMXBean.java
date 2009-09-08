/*
 * Copyright 2007-2009 Sun Microsystems, Inc.
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
 *
 * Sun designates this particular file as subject to the "Classpath"
 * exception as provided by Sun in the LICENSE file that accompanied
 * this code.
 */

package com.sun.sgs.management;

/**
 *  The management interface for the affinity group finder.
 */
public interface AffinityGroupFinderMXBean {
    /** The name for uniquely identifying this MBean. */
    String MXBEAN_NAME = "com.sun.sgs:type=AffinityGroupFinder";

    /**
     * Returns the number of groups found in the latest run of the
     * affinity group finder.
     * @return the number of groups found in the latest run
     */
    long getNumberGroups();

    /**
     * Returns the number of times the affinity group finder has run,
     * including runs that fail.
     * @return the number of times the affinity group finder has run
     */
    long getNumberRuns();

    /**
     * Returns the number of times the affinity group finder has failed,
     * due to errors or node failures during a run.
     * @return the number of times the affinity group finder has failed
     */
    long getNumberFailures();

    /**
     * Returns the number of times the affinity group finder was stopped
     * due to not converging soon enough.  A stopped run is not a failed
     * run;  valid results are returned.
     * @return the number of times the affinity group finder was stopped
     */
    long getNumberStopped();

    /**
     * Returns the average amount of time, in milliseconds, spent in algorithm
     * runs.
     * @return the average amount of time spent in algorithm runs
     */
    long getAvgRunTime();

    /**
     * Returns the maximum amount of time, in milliseconds, spent in an
     * algorithm run.
     * @return the maximum amount of time spent in an algorithm run
     */
    long getMaxRunTime();

    /**
     * Returns the average number of iterations required for algorithm runs.
     * @return the average number of iterations required for algorithm runs
     */
    int getAvgIterations();

    /**
     * Returns the max number of iterations for any algorithm run.
     * @return the max number of iterations for any algorithm run
     */
    int getMaxIterations();

    /**
     * Returns the configured maximum number of iterations allowed to run
     * before stopping the algorithm and returning the current results.
     * @return the configured maximum number of iterations
     */
    int getStopIteration();

}
