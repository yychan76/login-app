package com.dxc.demo.loginapp.data.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PostLoad;
import jakarta.persistence.Transient;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAccount {

    @Version
    private int version;

    @NotEmpty
    @Builder.Default
    private String firstName = "";

    @NotEmpty
    @Builder.Default
    private String lastName = "";

    @NotEmpty
    @Column(unique = true)
    @Id
    @Builder.Default
    private String username = "";

    @NotEmpty
    @Builder.Default
    private String password = "";

    @NotNull
    @ManyToOne
    private Role role;

    @Transient
    private boolean isManager;

    @PostLoad
    private void postLoad() {
        this.isManager = role.getAuthority().equals("ROLE_MANAGER");
    }

}
