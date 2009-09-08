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

package com.sun.sgs.impl.service.nodemap.affinity;

import com.sun.sgs.management.AffinityGraphBuilderMXBean;
import com.sun.sgs.profile.AggregateProfileCounter;
import com.sun.sgs.profile.ProfileCollector;
import com.sun.sgs.profile.ProfileCollector.ProfileLevel;
import com.sun.sgs.profile.ProfileConsumer;
import com.sun.sgs.profile.ProfileConsumer.ProfileDataType;
import edu.uci.ics.jung.graph.Graph;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanInfo;
import javax.management.StandardMBean;

/**
 * The exposed management information for the affinity graph builder.
 */
public class AffinityGraphBuilderStats extends StandardMBean
        implements AffinityGraphBuilderMXBean
{
    private static final String NAME = "com.sun.sgs.AffinityGraphBuilder";
    // The graph
    private final Graph<?, ?> graph;

    // Configuration info
    private final int snapCount;
    private final long snapPeriod;

    // Counters that are updated by the builders
    private final AggregateProfileCounter processingTime;
    private final AggregateProfileCounter updateCount;
    private final AggregateProfileCounter pruneCount;

    /**
     * Constructs a stats instance.
     * @param collector the profile collector
     * @param graph the graphs
     * @param snapCount the configured snapshot count
     * @param snapPeriod the configured snapshot period
     */
    public AffinityGraphBuilderStats(ProfileCollector collector,
            Graph<?, ?> graph, int snapCount, long snapPeriod)
    {
        super(AffinityGraphBuilderMXBean.class, true);
        this.graph = graph;
        this.snapCount = snapCount;
        this.snapPeriod = snapPeriod;
        ProfileConsumer consumer = collector.getConsumer(NAME);
        ProfileLevel level = ProfileLevel.MIN;
        ProfileDataType type = ProfileDataType.AGGREGATE;

        processingTime = (AggregateProfileCounter)
                consumer.createCounter("processingTime", type, level);
        updateCount = (AggregateProfileCounter)
                consumer.createCounter("updateCount", type, level);
        pruneCount = (AggregateProfileCounter)
                consumer.createCounter("pruneCount", type, level);
    }

    /** {@inheritDoc} */
    public long getNumberEdges() {
        return graph.getEdgeCount();
    }

    /** {@inheritDoc} */
    public long getNumberVertices() {
        return graph.getVertexCount();
    }

    /** {@inheritDoc} */
    public long getProcessingTime() {
        return processingTime.getCount();
    }

    /** {@inheritDoc} */
    public long getUpdateCount() {
        return updateCount.getCount();
    }

    /** {@inheritDoc} */
    public long getPruneCount() {
        return pruneCount.getCount();
    }

    /** {@inheritDoc} */
    public int getSnapshotCount() {
        return snapCount;
    }

    /** {@inheritDoc} */
    public long getSnapshotPeriod() {
        return snapPeriod;
    }

    // Overrides for StandardMBean information, giving JMX clients
    // (like JConsole) more information for better displays.

    /** {@inheritDoc} */
    protected String getDescription(MBeanInfo info) {
        return "An MXBean for examining affinity graph builders";
    }

    /** {@inheritDoc} */
    protected String getDescription(MBeanAttributeInfo info) {
        String description = null;
        if (info.getName().equals("NumberEdges")) {
            description = "The number of edges in the affinity graph";
        } else if (info.getName().equals("NumberVertices")) {
            description = "The number of vertices in the affinity graph";
        } else if (info.getName().equals("ProcessingTime")) {
            description = "The total amount of time, in milliseconds, spent " +
                    "processing (modifying) the affinity graph.";
        } else if (info.getName().equals("UpdateCount")) {
            description = "The number of updates (additions) to the graph.";
        } else if (info.getName().equals("PruneCount")) {
            description = "Then number of times the graph was pruned (had dead"
                   +  " information removed";
        } else if (info.getName().equals("SnapshotCount")) {
            description = "The configured number of live snapshots of the"
                   + " graph to keep.";
        } else if (info.getName().equals("SnapshotPeriod")) {
            description = "The configured length of time, in milliseconds,"
                    + " for each snapshot.";
        }
        return description;
    }

    // Package updators
    void processingTimeInc(long inc) {
        processingTime.incrementCount(inc);
    }

    void updateCountInc() {
        updateCount.incrementCount();
    }

    void pruneCountInc() {
        pruneCount.incrementCount();
    }
}
