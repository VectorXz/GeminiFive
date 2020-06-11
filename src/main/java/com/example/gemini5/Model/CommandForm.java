package com.example.gemini5.Model;

import edu.gemini.app.ocs.model.VirtualTelescope;

public class CommandForm {
    private VirtualTelescope.COMMAND cmds;

    public VirtualTelescope.COMMAND getCmds() {
        return cmds;
    }

    public void setCmds(VirtualTelescope.COMMAND cmds) {
        this.cmds = cmds;
    }
}
