package br.infnet.tp1_guilda.domain.audit;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "permissions", schema = "audit")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "permissionsIdSeq")
    @SequenceGenerator(
            name = "permissionsIdSeq",
            sequenceName = "permissions_id_seq",
            allocationSize = 1,
            schema = "audit"
    )
    private Long id;

    @Column(name = "code", nullable = false, length = 80, unique = true)
    private String code;

    @Column(name = "descricao", nullable = false, length = 255)
    private String descricao;
}