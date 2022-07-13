package com.ratepay.bugtracker.bug.repository;

import com.ratepay.bugtracker.bug.model.Bug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BugRepository extends JpaRepository<Bug, Long> {
}
