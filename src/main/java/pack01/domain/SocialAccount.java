package pack01.domain;

import pack01.domain.type.SocialType;

public class SocialAccount {
    private Long socialAccountId;
    private SocialType type;
    private String accountId;
    private String link;
    private Long userId;

    public SocialAccount() {
    }

    public SocialAccount(Long socialAccountId, SocialType type, String accountId, String link, Long userId) {
        this.socialAccountId = socialAccountId;
        this.type = type;
        this.accountId = accountId;
        this.link = link;
        this.userId = userId;
    }

    public SocialAccount(SocialType type, String accountId, String link, Long userId) {
        this.type = type;
        this.accountId = accountId;
        this.link = link;
        this.userId = userId;
    }

    public Long getSocialAccountId() {
        return socialAccountId;
    }

    public SocialType getType() {
        return type;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getLink() {
        return link;
    }

    public Long getUserId() {
        return userId;
    }
}
