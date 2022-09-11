package practice.thankoo.member.application;

import org.springframework.context.ApplicationEvent;

public class MemberDeleteEvent extends ApplicationEvent {

    private final Long id;

    public MemberDeleteEvent(final Object object, final Long id) {
        super(object);
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
