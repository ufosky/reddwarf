/*
 * Copyright 2007 Sun Microsystems, Inc. All rights reserved
 */

package com.sun.sgs.kernel;


/**
 * This interface should be implemented by components that accept
 * profiling data associated with tasks that are running through the
 * scheduler.  Typically each consumer is matched with a
 * <code>ProfileProducer</code>.  Note that operations, counters and
 * samples are always handled in separate namespaces, so their
 * registrations will not collide.
 *
 * @see ProfileCounter
 * @see ProfileOperation
 * @see ProfileSample
 */
public interface ProfileConsumer {

    /**
     * Registers the named operation with this consumer, such that the
     * operation can be reported as part of a task's profile. Note
     * that registering the same name multiple times on the same
     * consumer may not produce the same instance of
     * <code>ProfileOperation</code>.  That is, two registrations of
     * the same name may still result in operations that are reported
     * distinctly.
     *
     * @param name the name of the operation
     *
     * @return an instance of <code>ProfileOperation</code>
     */
    public ProfileOperation registerOperation(String name);

    /**
     * Registers the named counter with this consumer, such that the
     * counter can be incremented during the run of a task. If this
     * counter is local to a task it means that each time a new task
     * runs, the counter is perceived as starting from zero for that
     * task. Note that registering the same name multiple times on the
     * same consumer <i>will</i> produce the same canonical instance of
     * {@code ProfileCounter}.
     *
     * @param name the name of the counter
     * @param taskLocal <code>true</code> if this counter is local to
     *        tasks, <code>false</code> otherwise
     *
     * @return an instance of <code>ProfileCounter</code>
     */
    public ProfileCounter registerCounter(String name, boolean taskLocal);

    /**
     * Registers the named source of data samples, and returns a
     * {@code ProfileSample} that can record each new datum during
     * the lifetime of a task or the application.  If the sample
     * counting should be local to a task, the current list of samples
     * will be empty upon each start of a task.  Note that registering
     * the same name multiple times on the same consumer <i>will</i>
     * produce the same canonical instance of {@code ProfileSample}.
     * <p>
     *  A negative value for {@code maxSamples} indicates an infinite
     *  number of samples.  Note that for non-task-local sample
     *  sources, this is a potential memory leak as the number of
     *  samples increases.  Once the limit of samples has been
     *  reached, older samples will be dropped to make room for the
     *  newest samples
     *
     * @param name a name or description of the sample type
     * @param taskLocal <code>true</code> if this counter is local to
     *        tasks, <code>false</code> otherwise
     * @param maxSamples the maximum number of samples to keep.  
     *
     * @return a {@code ProfileSample} that collects the data
     */
    public ProfileSample registerSampleSource(String name, boolean taskLocal,
					       long maxSamples);

}
