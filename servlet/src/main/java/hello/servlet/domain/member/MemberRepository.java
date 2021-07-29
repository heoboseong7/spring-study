package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
*  동시성 문제가 고려되어 있지 않음. 실무에서는 ConcurrentHashmap, AtomicLong 사용 고려
*/

public class MemberRepository {
    //static이 없어도 싱글톤이기 때문에 1개이다.
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    private static final MemberRepository instance = new MemberRepository();

    public static MemberRepository getInstance() {
        return instance;
    }
    // 싱글톤을 위해 private으로 생성자를 막는다.
    private MemberRepository() {
    }

    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public List<Member> findAll() {
        // store의 값들을 보존(보호)하기 위해서 새로운 리스트 생성
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
