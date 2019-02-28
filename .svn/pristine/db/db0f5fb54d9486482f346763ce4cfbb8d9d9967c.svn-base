/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.hqu.modules.paperreceive.receive.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.core.persistence.Page;
import com.jeeplus.core.service.CrudService;
import com.hqu.modules.paperreceive.receive.entity.Receive;
import com.hqu.modules.paperreceive.receive.mapper.ReceiveMapper;

/**
 * 订单管理Service
 * @author ylf
 * @version 2018-09-09
 */
@Service
@Transactional(readOnly = true)
public class ReceiveService extends CrudService<ReceiveMapper, Receive> {

	public Receive get(String id) {
		return super.get(id);
	}
	
	public List<Receive> findList(Receive receive) {
		return super.findList(receive);
	}
	
	public Page<Receive> findPage(Page<Receive> page, Receive receive) {
		return super.findPage(page, receive);
	}
	
	@Transactional(readOnly = false)
	public void save(Receive receive) {
		super.save(receive);
	}
	
	@Transactional(readOnly = false)
	public void delete(Receive receive) {
		super.delete(receive);
	}
	
}