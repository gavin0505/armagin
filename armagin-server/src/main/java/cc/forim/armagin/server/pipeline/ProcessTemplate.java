package cc.forim.armagin.server.pipeline;

import java.util.List;

/**
 * 业务执行模板
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/3/16 20:59
 */
public class ProcessTemplate {

    private List<BusinessProcess> processList;

    public List<BusinessProcess> getProcessList() {
        return processList;
    }

    public void setProcessList(List<BusinessProcess> processList) {
        this.processList = processList;
    }
}
