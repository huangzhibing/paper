package com.hqu.modules.craw.core;

import com.hqu.modules.craw.core.entity.Identity;
import com.hqu.modules.craw.core.entity.MetaType;
import com.hqu.modules.craw.core.entity.TargetData;
import com.hqu.modules.craw.core.entity.TargetURL;
import com.hqu.modules.craw.task.entity.Task;
import com.hqu.modules.craw.util.CrawUtils;
import us.codecraft.webmagic.Page;

import java.util.List;

public class MultiStepCore implements Core {
    @Override
    public List<String> getTargetUrl(Task task, Page page) {
        for(TargetURL targetURL: task.getTargetURLList()) {
            Identity identity = targetURL.getIdentity();
            if(MetaType.XPATH.equals(identity.getMetaType())) {
                if(page.getHtml().xpath(identity.getValue()).toString().equals(identity.getEqualsTo())) {
                    List<String> target = getAllTarget(targetURL,page);
                    if(target != null) {
                        page.addTargetRequests(target);
                        task.setTotalUrlSize(task.getTotalUrlSize() + target.size());
                    }
                }
            } else if(MetaType.REGEX.equals(identity.getMetaType())) {
                if(page.getHtml().regex(identity.getValue()).toString().equals(identity.getEqualsTo())) {
                    List<String> target = getAllTarget(targetURL,page);
                    if(target != null) {
                        page.addTargetRequests(target);
                        task.setTotalUrlSize(task.getTotalUrlSize() + target.size());
                    }
                }
            } else if(MetaType.JSOUP.equals(identity.getMetaType())) {

            } else if(MetaType.SELECTOR.equals(identity.getMetaType())) {
                if (page.getHtml().$(identity.getValue()).toString().equals(identity.getEqualsTo())) {
                    List<String> target = getAllTarget(targetURL, page);
                    if (target != null) {
                        page.addTargetRequests(target);
                        task.setTotalUrlSize(task.getTotalUrlSize() + target.size());
                    }
                }
            }
        }
        return null;
    }

    private List<String> getAllTarget(TargetURL targetURL, Page page) {
        if(MetaType.XPATH.equals(targetURL.getMetaType())) {
            return CrawUtils.handleAndSymbol(page.getHtml().xpath(targetURL.getValue()).all());
        } else if(MetaType.REGEX.equals(targetURL.getMetaType())) {
            return CrawUtils.handleAndSymbol(page.getHtml().regex(targetURL.getValue()).all());
        } else if(MetaType.JSOUP.equals(targetURL.getMetaType())) {
//            return CrawUtils.handleAndSymbol(page.getHtml().(targetURL.getValue()).all());
        } else if(MetaType.SELECTOR.equals(targetURL.getMetaType())) {
            return CrawUtils.handleAndSymbol(page.getHtml().$(targetURL.getValue()).all());
        }
        return null;
    }

    private void getData(TargetData targetData, Page page) {
        if(MetaType.XPATH.equals(targetData.getMetaType())) {
            page.putField(targetData.getName(),page.getHtml().xpath(targetData.getValue()));
        } else if(MetaType.REGEX.equals(targetData.getMetaType())) {
            page.putField(targetData.getName(),page.getHtml().regex(targetData.getValue()));
        } else if(MetaType.JSOUP.equals(targetData.getMetaType())) {
//            return CrawUtils.handleAndSymbol(page.getHtml().(targetData.getValue()).all());
        } else if(MetaType.SELECTOR.equals(targetData.getMetaType())) {
            page.putField(targetData.getName(),page.getHtml().$(targetData.getValue()));
        }
    }

    @Override
    public void getTargetData(Task task, Page page) {
        if(checkIsDataPage(task,page)) {
            for (TargetData targetData : task.getTargetDataList()) {
                getData(targetData,page);
            }
        }
    }

    private boolean checkIsDataPage(Task task, Page page) {
        boolean isDataPage = false;
        Identity identity = task.getDataPageIdentity();
        if (MetaType.XPATH.equals(identity.getMetaType())) {
            if (page.getHtml().xpath(identity.getValue()).toString().equals(identity.getEqualsTo())) {
                isDataPage = true;
            }
        } else if (MetaType.REGEX.equals(identity.getMetaType())) {
            if (page.getHtml().regex(identity.getValue()).toString().equals(identity.getEqualsTo())) {
                isDataPage = true;
            }
        } else if (MetaType.JSOUP.equals(identity.getMetaType())) {
            if (page.getHtml().xpath(identity.getValue()).toString().equals(identity.getEqualsTo())) {
                isDataPage = true;
            }
        } else if (MetaType.SELECTOR.equals(identity.getMetaType())) {
            if (page.getHtml().$(identity.getValue()).toString().equals(identity.getEqualsTo())) {
                isDataPage = true;
            }
        }

        return isDataPage;
    }
}
