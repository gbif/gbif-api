package org.gbif.api.service.occurrence;

import javax.validation.constraints.NotNull;

public interface DownloadLauncherService {

  void cancelJob(@NotNull String jobId);

  void unlockAll();

  void unlock(@NotNull String jobId);
}
