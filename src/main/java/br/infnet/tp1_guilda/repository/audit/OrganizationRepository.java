package br.infnet.tp1_guilda.repository.audit;

import br.infnet.tp1_guilda.domain.audit.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
}