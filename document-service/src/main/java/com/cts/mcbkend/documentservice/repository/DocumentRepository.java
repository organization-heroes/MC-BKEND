package com.cts.mcbkend.documentservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cts.mcbkend.documentservice.entity.DocumentEntity;

public interface DocumentRepository extends JpaRepository<DocumentEntity, Integer> {
	public List<DocumentEntity> findByUserId(Long userId);
	public DocumentEntity findByLoanNumAndDocId(String loanNum, String docId);
	public DocumentEntity findById(Long id);
	public List<DocumentEntity> findByUserIdAndLoanNum(Long userId, String loanNum);
	public List<DocumentEntity> findByLoanNum(String loanNum);
	public List<DocumentEntity> findByApprvlStatusNot(String apprvlStatus);
	public long countByUserIdAndLoanNum(Long userId , String loanNum);
	public long deleteByLoanNumAndDocId(String loanNum, String docId);
	@Modifying(flushAutomatically = true)
	@Query("update DocumentEntity d set d.apprvlStatus  = :apprvlStatus where d.loanNum = :loanNum  and d.docId = :docId")
	public long updateApprovalStatus(@Param("apprvlStatus") String apprvlStatus, @Param("loanNum") String loanNum, @Param("docId") String docId);
}