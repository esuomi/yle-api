package io.induct.yle.api;

import io.induct.yle.api.programs.YleProgramsApi;

import javax.inject.Inject;

/**
 * @since 2015-05-09
 */
public class YleApi {

    private final YleProgramsApi programsApi;

    @Inject
    public YleApi(YleProgramsApi programsApi) {
        this.programsApi = programsApi;
    }

    public YleProgramsApi programs() {
        return programsApi;
    }
}
