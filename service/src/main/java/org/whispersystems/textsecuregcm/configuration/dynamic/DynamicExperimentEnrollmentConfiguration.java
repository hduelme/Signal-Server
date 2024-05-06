/*
 * Copyright 2021 Signal Messenger, LLC
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.whispersystems.textsecuregcm.configuration.dynamic;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.annotations.VisibleForTesting;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Collections;
import java.util.Set;
import java.util.UUID;

public class DynamicExperimentEnrollmentConfiguration {

  public static class UuidSelector {

    @JsonProperty
    @Valid
    private Set<UUID> uuids = Collections.emptySet();

    /**
     * What percentage of enrolled UUIDs should the experiment be enabled for.
     * <p>
     * Unlike {@link this#enrollmentPercentage}, this is not stable by UUID. The same UUID may be
     * enrolled/unenrolled across calls.
     */
    @JsonProperty
    @Valid
    @Min(0)
    @Max(100)
    private int uuidEnrollmentPercentage = 100;

    public Set<UUID> getUuids() {
      return uuids;
    }

    public int getUuidEnrollmentPercentage() {
      return uuidEnrollmentPercentage;
    }

  }

  private UuidSelector uuidSelector = new UuidSelector();

  /**
   * If the UUID is not enrolled via {@link UuidSelector#uuids}, what is the percentage chance it should be enrolled.
   * <p>
   * This is stable by UUID, for a given configuration if a UUID is enrolled it will always be enrolled on every call.
   */
  @JsonProperty
  @Valid
  @Min(0)
  @Max(100)
  private int enrollmentPercentage = 0;

  public int getEnrollmentPercentage() {
    return enrollmentPercentage;
  }

  public UuidSelector getUuidSelector() {
    return uuidSelector;
  }
}
