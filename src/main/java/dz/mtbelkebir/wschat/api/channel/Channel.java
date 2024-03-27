package dz.mtbelkebir.wschat.api.channel;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import dz.mtbelkebir.wschat.api.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Channels")
@Data
public class Channel {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String title;

    @ManyToOne(optional = false)
    @JoinColumn(name = "owner_id")
    @JsonIgnoreProperties({"ownedChannels", "channelMemberships"})
    private User owner;

    @ManyToMany
    @JoinTable(name = "channel_memberships", joinColumns = @JoinColumn(name = "channel_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> members;
}
