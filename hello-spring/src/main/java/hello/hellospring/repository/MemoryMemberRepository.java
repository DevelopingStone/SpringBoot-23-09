package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

//    @Override
//    public Optional<Member> findByName(String name) {
//        return store.values().stream()
//                .filter(member -> member.getName().equals(member))
//                .findAny();
//    }


    public Optional<Member> findByName(String name) {
        for (Member member : store.values()) {
            if (member.getName().equals(name)) {
                return Optional.of(member);
            }
        }
        return Optional.empty();
    }


    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
